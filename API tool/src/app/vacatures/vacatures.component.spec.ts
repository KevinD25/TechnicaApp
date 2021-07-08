import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacaturesComponent } from './vacatures.component';

describe('VacaturesComponent', () => {
  let component: VacaturesComponent;
  let fixture: ComponentFixture<VacaturesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacaturesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacaturesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
