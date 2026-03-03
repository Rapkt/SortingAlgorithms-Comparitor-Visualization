import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-cards',
  imports: [],
  templateUrl: './cards.html',
  styleUrl: './cards.css',
})
export class Cards {
  @Input() name: string='';
  @Input() imageUrl: string='';
  @Input() description: string='';
  @Input() isSelected: boolean=false;
  @Input() complexity: string='';
  @Input() selectionType: 'A' | 'B' | null = null;

  @Output() selected = new EventEmitter<void>();

  onSelect(){
    this.selected.emit();
  }
}
