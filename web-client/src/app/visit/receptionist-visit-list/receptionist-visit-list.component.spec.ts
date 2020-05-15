import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceptionistVisitListComponent } from './receptionist-visit-list.component';

describe('ReceptionistVisitListComponent', () => {
  let component: ReceptionistVisitListComponent;
  let fixture: ComponentFixture<ReceptionistVisitListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReceptionistVisitListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReceptionistVisitListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
