import { TestBed } from '@angular/core/testing';

import { PhysicalExaminationDetailsService } from './physical-examination-details.service';

describe('PhysicalExaminationDetailsService', () => {
  let service: PhysicalExaminationDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PhysicalExaminationDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
