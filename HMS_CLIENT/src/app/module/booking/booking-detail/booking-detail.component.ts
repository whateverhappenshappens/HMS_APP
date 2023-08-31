import { Component } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Observable, map, catchError } from 'rxjs';
import { NewBookingResponse } from 'src/app/interfaces/newBookingResponse.interface';
import { BookingService } from 'src/app/services/booking.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { NewBookingService } from 'src/app/services/new-booking.service';

@Component({
  selector: 'app-booking-detail',
  templateUrl: './booking-detail.component.html',
  styleUrls: ['./booking-detail.component.css']
})
export class BookingDetailComponent {

  bookingForm!: FormGroup;
  booking!: NewBookingResponse;
  bookingId!:number
  bookingDetailForm: any;
  constructor(private fb:FormBuilder,private newBooking:NewBookingService,private toastr:ToastrService,private newDoctor:DoctorService,private bookingService:BookingService){}
  ngOnInit(): void {
    this.bookingForm = this.fb.group({
      bookingId: [''],
    });
  }
  fetchBookings(): void {
    //  this.userID =this.bookingForm.get('userId')!.value;
    //  console.log(this.userID);
    // this.bookingService.getBookings(this.userID).subscribe({
    //   next:(bookingList:)=>{
    //     this.bookings=bookingList;
    //   },
    //   error:(error)=>{
    //     this.toastr.error('Error fetching booking',error);
    //   }
    //   });
     this.bookingId = this.bookingForm.get('bookingId')?.value;
     this.bookingService.getBookingDeailsById(this.bookingId).subscribe({
      next:(bookingDetail:NewBookingResponse)=>{
        this.booking=bookingDetail
      }
     });
    
  }

  


}
