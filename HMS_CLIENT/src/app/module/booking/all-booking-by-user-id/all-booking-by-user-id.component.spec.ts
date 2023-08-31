import { ComponentFixture, TestBed } from '@angular/core/testing';


import { AllBookingByUserIdComponent } from './all-booking-by-user-id.component';

describe('AllBookingByUserIdComponent', () => {
  let component: AllBookingByUserIdComponent;
  let fixture: ComponentFixture<AllBookingByUserIdComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllBookingByUserIdComponent]
    });
    fixture = TestBed.createComponent(AllBookingByUserIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
