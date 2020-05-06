import { TestBed } from '@angular/core/testing';

import { LaboratoryExaminationService } from './laboratory-examination.service';

describe('LaboratoryExaminationService', () => {
  let service: LaboratoryExaminationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LaboratoryExaminationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
