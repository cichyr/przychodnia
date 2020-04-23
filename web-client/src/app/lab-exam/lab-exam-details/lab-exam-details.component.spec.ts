import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LabExamDetailsComponent } from './lab-exam-details.component';

describe('LabExamDetailsComponent', () => {
  let component: LabExamDetailsComponent;
  let fixture: ComponentFixture<LabExamDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LabExamDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LabExamDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
