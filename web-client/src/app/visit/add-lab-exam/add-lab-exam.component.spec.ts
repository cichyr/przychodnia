import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLabExamComponent } from './add-lab-exam.component';

describe('AddLabExamComponent', () => {
  let component: AddLabExamComponent;
  let fixture: ComponentFixture<AddLabExamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddLabExamComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLabExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
