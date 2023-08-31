import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup,ReactiveFormsModule, ValidatorFn, Validators,FormControl,FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Observable, catchError, map } from 'rxjs';
import { BookingService } from 'src/app/services/booking.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { NewBookingService } from 'src/app/services/new-booking.service';

@Component({
  selector: 'app-all-booking-by-user-id',
  templateUrl: './all-booking-by-user-id.component.html',
  styleUrls: ['./all-booking-by-user-id.component.css']
})
export class AllBookingByUserIdComponent implements OnInit {
  bookingForm!: FormGroup;
  bookings$!: Observable<any[]>;
  userID!:number
  constructor(private fb:FormBuilder,private newBooking:NewBookingService,private toastr:ToastrService,private newDoctor:DoctorService,private bookingService:BookingService){}
  ngOnInit(): void {
    this.bookingForm = this.fb.group({
      userId: [''],
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
     this.userID = this.bookingForm.get('userId')?.value;
    this.bookings$ = this.bookingService.getBookings(this.userID).pipe(
      map(response => response.bookings.content),
      catchError(error => {
        console.error('Error fetching bookings:', error);
        return [];
      })
    );
    
  }

  



}
