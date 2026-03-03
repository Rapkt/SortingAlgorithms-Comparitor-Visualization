import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Algorithm, AlgorithmService } from '../../Service/Algo';
import { Cards } from '../shared/cards/cards';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { BenchmarkRequest, BenchMarkResults, SortService } from '../../Service/SortService';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-comparisons',
  imports: [Cards, FormsModule, CommonModule, RouterLink],
  templateUrl: './comparisons.html',
  styleUrl: './comparisons.css',
})
export class Comparisons implements OnInit {
  algorithmList: Algorithm[] = [];
  selectedAlgoA: Algorithm | null = null;
  selectedAlgoB: Algorithm | null = null;
  lastSelectedAlgo: Algorithm | null = null;
  currentRotation = 0;
  arraySize = 1000;
  iterations = 10;
  ShowResults: boolean = false;
  arrayType: string = 'Random';
  isLoading: boolean = false;

  resultsA: BenchMarkResults | null = null;
  resultsB: BenchMarkResults | null = null;

  constructor(
    private algoService: AlgorithmService,
    private srtService: SortService,
    private cdr: ChangeDetectorRef,
  ) {}
  ngOnInit() {
    this.algorithmList = this.algoService.getAlgorithm();
    console.log(this.algorithmList);
  }

  getSelectionType(algoId: string): 'A' | 'B' | null {
    if (this.selectedAlgoA?.id == algoId) return 'A';
    if (this.selectedAlgoB?.id == algoId) return 'B';
    return null;
  }
  handleSelect(algo: any) {
    if (this.selectedAlgoA?.id === algo.id) {
      this.selectedAlgoA = null;
    } else if (this.selectedAlgoB?.id === algo.id) {
      this.selectedAlgoB = null;
    } else if (!this.selectedAlgoA) {
      this.selectedAlgoA = algo;
      this.lastSelectedAlgo = algo;
    } else if (!this.selectedAlgoB) {
      this.selectedAlgoB = algo;
      this.lastSelectedAlgo = algo;
    } else {
      if (this.selectedAlgoA?.id === this.lastSelectedAlgo?.id) {
        this.selectedAlgoA = algo;
        this.lastSelectedAlgo = algo;
      } else {
        this.selectedAlgoB = algo;
        this.lastSelectedAlgo = algo;
      }
    }
  }
  isAlgoSelected(id: string): boolean {
    return this.selectedAlgoA?.id === id || this.selectedAlgoB?.id === id;
  }

  startBenchMark() {
    if (!this.selectedAlgoA || !this.selectedAlgoB) return;
    this.isLoading = true;
    const request: BenchmarkRequest = {
      algoIdA: this.selectedAlgoA.id,
      algoIdB: this.selectedAlgoB.id,
      size: this.arraySize,
      iterations: this.iterations,
      type: this.arrayType,
    };
    console.log(request);
    this.srtService.runBenchmarks(request).subscribe({
      next: (data) => {
        console.log('Data received:', data);
        this.resultsA = data.resultsA;
        this.resultsB = data.resultsB;

        this.isLoading = false;
        this.ShowResults = true;

        this.cdr.detectChanges();
      },
      error: (error) => {
        this.isLoading = false;
        console.error('benchmark failed: ', error);
      },
    });
  }

  getCardStyle(index: number) {
    const total = this.algorithmList.length;
    const radius = 450;

    const angleStart = 180;
    const angleEnd = 0;
    const angleStep = (angleStart - angleEnd) / (total - 1);

    const currentAngle = angleStart - index * angleStep;
    const radians = (currentAngle * Math.PI) / 180;

    const x = radius * Math.cos(radians);
    const y = radius * Math.sin(radians) * -1;

    return {
      transform: `translate(calc(-50% + ${x}px), ${y}px)`,
    };
  }
}
