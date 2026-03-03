import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Vis } from './vis';

describe('Vis', () => {
  let component: Vis;
  let fixture: ComponentFixture<Vis>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Vis],
    }).compileComponents();

    fixture = TestBed.createComponent(Vis);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
