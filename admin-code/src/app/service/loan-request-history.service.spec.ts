import { TestBed } from '@angular/core/testing';

import { LoanRequestHistoryService } from './loan-request-history.service';

describe('LoanRequestHistoryService', () => {
  let service: LoanRequestHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanRequestHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
