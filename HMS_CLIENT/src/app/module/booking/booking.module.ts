import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddBookingComponent } from './add-booking/add-booking.component';
import { BookingRoutingModule } from './booking-routing.module';
import { FormBuilder, FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from "@angular/router";
import { SharedModule } from 'src/app/shared/shared.module';
import { AllBookingByUserIdComponent } from './all-booking-by-user-id/all-booking-by-user-id.component';
import { MaterialModule } from 'src/app/material.module';
import { UserDetailsByIdComponent } from './user-details-by-id/user-details-by-id.component';
import { BookingDetailComponent } from './booking-detail/booking-detail.component';
import { AllBookingByUserComponent } from './all-booking-by-user/all-booking-by-user.component';
import { ReactivateUserAccountComponent } from './reactivate-user-account/reactivate-user-account.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';



@NgModule({
  declarations: [
    AddBookingComponent,
    AllBookingByUserIdComponent,
    UserDetailsByIdComponent,
    BookingDetailComponent,
    AllBookingByUserComponent,
    ReactivateUserAccountComponent
  ],
  imports: [
    CommonModule,
    BookingRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SharedModule,
    MaterialModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 3000, // Time in milliseconds to close the toastr automatically
      positionClass: 'toast-top-right', // Position of the toastr
      preventDuplicates: true, // Prevent duplicate toasts
    }),
  ]
})
export class BookingModule { }
