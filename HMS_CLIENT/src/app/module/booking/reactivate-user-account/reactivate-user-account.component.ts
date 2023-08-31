import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AccountService } from 'src/app/services/account.service';
import { BookingService } from 'src/app/services/booking.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { NewBookingService } from 'src/app/services/new-booking.service';

@Component({
  selector: 'app-reactivate-user-account',
  templateUrl: './reactivate-user-account.component.html',
  styleUrls: ['./reactivate-user-account.component.css']
})
export class ReactivateUserAccountComponent {
  inputForm!: FormGroup;
  blockUserInputForm!:FormGroup
  userId!:number
  constructor(private fb:FormBuilder,private newBooking:NewBookingService,private toastr:ToastrService,private newDoctor:DoctorService,private bookingService:BookingService
   , private accountService:AccountService
    ){}
  ngOnInit(): void {
  this.inputForm = this.fb.group({
    userId: [''],
  });
  this.blockUserInputForm = this.fb.group({
    userId: [''],
  });
}
  reactivtateUser() {
    this.userId = this.inputForm.get('userId')?.value;
     this.accountService.reactivateAccount(this.userId).subscribe({
      next:(response:boolean)=>{
        if(response==true){
          console.log("reactivated account with user id",this.userId);
        }
      },
      error:(error)=>{
        console.log("couldnt reactivate account",error);
      }
     });
    }
    blockUserAccount() {
      this.userId = this.blockUserInputForm.get('userId')?.value;
       this.accountService.blockUserAccount(this.userId).subscribe({
        next:(response:boolean)=>{
          if(response==true){
            console.log("blocked account with user id",this.userId);
          }
        },
        error:(error)=>{
          console.log("couldnt block account",error);
        }
       });
      }
}
