import {
  Component,
  ElementRef,
  OnInit,
  ViewChild,
  HostListener,
  AfterViewInit,
  ChangeDetectorRef
} from '@angular/core';
import * as d3 from 'd3';
import {SortService} from '../../Service/SortService';
import { Steps } from '../../Models/Steps';
import { firstValueFrom, lastValueFrom } from 'rxjs';
import { NgForOf, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Algorithm, AlgorithmService } from '../../Service/Algo';
import { Cards } from '../shared/cards/cards';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-vis',
  imports: [NgIf, FormsModule, NgForOf, Cards, RouterLink],
  templateUrl: './vis.html',
  styleUrl: './vis.css',
})
export class Vis implements OnInit, AfterViewInit {
  @ViewChild('visualizerContainer') set container(content: ElementRef) {
    if (content) {
      this._container = content;
      setTimeout(() => {
        this.createSvg();
        this.drawBars();
      }, 1);
    }
  }
  private _container!: ElementRef;

  constructor(
    private sortService: SortService,
    private algo: AlgorithmService,
    private cdx: ChangeDetectorRef,
  ) {}

  private lastInterchangeCount = 0;
  private svg: any;
  private margin = { top: 20, right: 20, bottom: 20, left: 20 };
  private width = 800;
  private height = 400;

  private currentPage = 1;
  private pageSize = 100;
  data: number[] = [];

  arraySize: number = 0;
  protected animationActive = false;
  animationSpeed = 50;
  private steps: any[] = [];
  private initialData: number[] = [];
  currentStep: Steps | null = null;
  arrayType: string = 'Random';
  algoId: string = '';
  algorithmList: Algorithm[] = [];

  ngOnInit() {
    this.algorithmList = this.algo.getAlgorithm();
  }
  ngAfterViewInit() {
    this.createSvg();
    this.drawBars();
  }

  private createSvg() {
    if (!this._container) return;
    d3.select(this._container.nativeElement).selectAll('svg').remove();

    this.svg = d3
      .select(this._container.nativeElement)
      .append('svg')
      .attr('width', '100%')
      .attr('height', this.height)
      .attr('viewBox', `0 0 ${this.width} ${this.height}`)
      .append('g')
      .attr('transform', `translate(${this.margin.left},${this.margin.top})`);
  }

  private drawBars() {
    if (!this.svg) return;
    const chartWidth = this.width - this.margin.left - this.margin.right;
    const chartHeight = this.height - this.margin.top - this.margin.bottom;

    const x = d3
      .scaleBand()
      .range([0, chartWidth])
      .domain(this.data.map((d, i) => i.toString()))
      .padding(0.2);

    const y = d3
      .scaleLinear()
      .domain([0, d3.max(this.data) || 100])
      .range([chartHeight, 0]);

    this.svg
      .selectAll('.bar')
      .data(this.data, (d: any, i: number) => i) // Key by index
      .join('rect')
      .attr('class', 'bar')
      .attr('x', (d: any, i: number) => x(i.toString()))
      .attr('y', (d: any) => y(d))
      .attr('width', x.bandwidth())
      .attr('height', (d: any) => chartHeight - y(d))
      .attr('fill', '#3498db') // Same blue as Algo A
      .attr('rx', 4); // Slightly rounded corners
  }

  async startAnimation() {
    if (this.animationActive || this.data.length === 0) return; // Prevent double-clicking play
    this.animationActive=true;
    this.cdx.detectChanges();
    try {
      if (this.svg) {
        this.svg.selectAll('.bar').interrupt().style('fill', '#3498db');
      }
      await firstValueFrom(this.sortService.triggerTheater(this.algoId));
      this.currentPage = 1;

      while (this.animationActive) {
        const newSteps = await lastValueFrom(
          this.sortService.getSteps(this.currentPage, this.pageSize),
        );
        if (!newSteps || newSteps.length == 0) break;
        for (const step of newSteps) {
          if (!this.animationActive) break;
          await this.processStep(step);
          this.cdx.detectChanges();
        }
        this.currentPage++;
      }
    } catch (error) {
      console.log('Animation failed ', error);
    } finally {
      this.animationActive = false;
      this.svg
        .selectAll('.bar')
        .classed('sorted', true)
        .transition()
        .duration(500)
        .style('fill', '#2ecc71');
      this.cdx.detectChanges();
    }
  }

  private async processStep(step: any) {
    this.data = [...step.currentList];

    this.currentStep = step;
    this.cdx.detectChanges();
    await this.updateBars();
    //this.highlightBars(step.startIdx,step.level);
    //this.highlightBars([step.i, step.j], step.interchanges > this.lastInterchangeCount ? 'swapping' : 'comparing');
    this.lastInterchangeCount = step.interchanges;
    this.cdx.detectChanges();
  }

  resetTheater() {
    this.animationActive = false;
    this.lastInterchangeCount = 0;
    if (this.svg) {
      this.svg.selectAll('.bar').interrupt().style('fill', '#3498db');
    }
    this.data = [...this.initialData];
    this.currentPage = 1;
    this.currentStep = null;
    this.drawBars();
    this.cdx.detectChanges();
  }

  private updateBars(): Promise<void> {
    if (!this.svg) return Promise.resolve();
    const chartHeight = this.height - this.margin.top - this.margin.bottom;
    const y = d3
      .scaleLinear()
      .domain([0, d3.max(this.data) || 100])
      .range([chartHeight, 0]);

    return this.svg
      .selectAll('.bar')
      .data(this.data, (d: any, i: number) => i)
      .transition()
      .duration(this.animationSpeed)
      .attr('y', (d: any) => y(d))
      .attr('height', (d: any) => chartHeight - y(d))
      .end();
  }
  private highlightBars(indices: number[], className: string) {
    this.svg
      .selectAll('.bar')
      .interrupt()
      .classed('comparing', false)
      .classed('swapping', false)
      .filter((d: any, i: number) => indices.includes(i))
      .classed(className, true);
    this.cdx.detectChanges();
  }

  async initializetheater(algoId: string, type: string, size: number, customArr: number[]) {
    this.animationActive = false;
    this.resetTheater();
    this.currentPage = 1;
    this.sortService.startVis(algoId, type, size, customArr)?.subscribe({
      next: (arr) => {
        console.log(arr);
        this.initialData = [...arr];
        this.data = [...arr];
        if (this.svg) {
          this.drawBars();
        }
        this.cdx.detectChanges();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  selectAlgo(id: string) {
    this.algoId = id;
    setTimeout(() => {
      const element = document.getElementById('lab-settings');
      if (element) {
        element.scrollIntoView({
          behavior: 'smooth',
          block: 'start',
          inline: 'nearest',
        });
      } else {
        window.scrollTo({ top: 400, behavior: 'smooth' });
      }
    }, 100);
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        const content = reader.result as string;
        const numbers = content
          .trim()
          .split(/,/)
          .map((s) => s.trim())
          .map(Number)
          .filter((n) => !isNaN(n));

        if (numbers.length > 0) {
          this.initializetheater(this.algoId, '', numbers.length, numbers);
        }
      };
      reader.readAsText(file);
    }
  }
}
