import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysicalExamDetailsComponent } from './physical-exam-details.component';

describe('PhysicalExamDetailsComponent', () => {
  let component: PhysicalExamDetailsComponent;
  let fixture: ComponentFixture<PhysicalExamDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PhysicalExamDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PhysicalExamDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
