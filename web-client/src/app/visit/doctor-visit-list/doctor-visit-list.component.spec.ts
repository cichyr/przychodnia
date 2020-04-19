import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorVisitListComponent } from './doctor-visit-list.component';

describe('DoctorVisitListComponent', () => {
  let component: DoctorVisitListComponent;
  let fixture: ComponentFixture<DoctorVisitListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorVisitListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorVisitListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
