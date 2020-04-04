import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LabExamListComponent } from './lab-exam-list.component';

describe('LabExamListComponent', () => {
  let component: LabExamListComponent;
  let fixture: ComponentFixture<LabExamListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LabExamListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LabExamListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
