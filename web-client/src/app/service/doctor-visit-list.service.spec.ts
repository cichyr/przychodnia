import { TestBed } from '@angular/core/testing';

import { DoctorVisitListService } from './doctor-visit-list.service';

describe('DoctorVisitListService', () => {
  let service: DoctorVisitListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DoctorVisitListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
