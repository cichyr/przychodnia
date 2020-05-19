import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPhysicalExamComponent } from './add-physical-exam.component';

describe('AddPhysicalExamComponent', () => {
  let component: AddPhysicalExamComponent;
  let fixture: ComponentFixture<AddPhysicalExamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPhysicalExamComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPhysicalExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
