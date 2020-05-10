import { TestBed } from '@angular/core/testing';

import { VisitDetailsService } from './visit-details.service';

describe('VisitDetailsService', () => {
  let service: VisitDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VisitDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
