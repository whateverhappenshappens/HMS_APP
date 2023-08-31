import { TestBed } from '@angular/core/testing';

import { NewBookingService } from './new-booking.service';

describe('NewBookingService', () => {
  let service: NewBookingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NewBookingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
