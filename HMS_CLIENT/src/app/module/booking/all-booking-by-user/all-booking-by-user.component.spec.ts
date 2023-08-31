import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllBookingByUserComponent } from './all-booking-by-user.component';

describe('AllBookingByUserComponent', () => {
  let component: AllBookingByUserComponent;
  let fixture: ComponentFixture<AllBookingByUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllBookingByUserComponent]
    });
    fixture = TestBed.createComponent(AllBookingByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
