import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PraesidiumComponent } from './praesidium.component';

describe('PraesidiumComponent', () => {
  let component: PraesidiumComponent;
  let fixture: ComponentFixture<PraesidiumComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PraesidiumComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PraesidiumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
