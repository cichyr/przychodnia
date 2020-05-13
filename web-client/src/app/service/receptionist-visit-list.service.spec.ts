import { TestBed } from '@angular/core/testing';

import { ReceptionistVisitListService } from './receptionist-visit-list.service';

describe('ReceptionistVisitListService', () => {
  let service: ReceptionistVisitListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReceptionistVisitListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
