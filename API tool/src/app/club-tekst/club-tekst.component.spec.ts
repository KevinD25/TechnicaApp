import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClubTekstComponent } from './club-tekst.component';

describe('ClubTekstComponent', () => {
  let component: ClubTekstComponent;
  let fixture: ComponentFixture<ClubTekstComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClubTekstComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClubTekstComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
