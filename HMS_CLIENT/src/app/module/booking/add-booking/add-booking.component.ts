import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators,FormControl,FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { DoctorSpecialization } from 'src/app/enums/DoctorSpecialization.enum';
import { Gender } from 'src/app/enums/gender.enum';
import { Doctor } from 'src/app/interfaces/Doctor.interface';
import { NewBookingRequest } from 'src/app/interfaces/newBookingRequest.interface';
import { NewBookingResponse } from 'src/app/interfaces/newBookingResponse.interface';
import { BookingService } from 'src/app/services/booking.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { NewBookingService } from 'src/app/services/new-booking.service';


function alphabetsOnlyValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const alphabetsOnlyRegex = /^[A-Za-z]+$/;
    const isValid = alphabetsOnlyRegex.test(control.value);
    return isValid ? null : { alphabetsOnly: { message: 'Name should only contain alphabets.' } };
  };
}
function validAge(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const validAgeRegex=/^(?:1[01][0-9]|120|1[7-9]|[2-9][0-9])$/;
    const isValid = validAgeRegex.test(control.value);
    return isValid ? null : { validAgeError: { message: 'Ages between 1-120 only for child below 1 year of age type 1' } };
  };
}

@Component({
  selector: 'app-add-booking',
  templateUrl: './add-booking.component.html',
  styleUrls: ['./add-booking.component.css']
})
export class AddBookingComponent implements OnInit {

  doctorSpecializations: string[];
  selectedSpecialization!: string;
  newBookingRequestForm!:FormGroup;
  doctors: Doctor[] = [];
  genders = Object.values(Gender);
  newBookingRequest!:NewBookingRequest
  newBookingResponse!:NewBookingResponse
  constructor(private fb:FormBuilder,private bookingService:BookingService,private toastr:ToastrService,private newDoctor:DoctorService){
    this.doctorSpecializations = this.getDoctorSpecializationStrings();
  }
  ngOnInit(): void {
    this.newBookingRequestForm = this.fb.group({
      patientName: ['', [Validators.required,alphabetsOnlyValidator()]],
      patientAge: ['', [Validators.required,validAge()]],
      bookingTime: ['', [Validators.required]],
      gender: ['', Validators.required],
      doctorId: ['', Validators.required],
      doctorSpecialization:['',Validators.required]
    });
  }
  
  getDoctorSpecializationStrings(): string[] {
    var speci =  Object.values(DoctorSpecialization).map(specialization => specialization.toString());
    console.log("spec: ", speci)
    return speci
  }
  getDoctorsBySpecialization(){
    this.selectedSpecialization=this.newBookingRequestForm.get('doctorSpecialization')?.value;
    if(this.doctorSpecializations){
    this.newDoctor.getDoctorsBySpecialization(this.selectedSpecialization).subscribe({
    next:(doctorsList:Doctor[])=>{
      this.doctors=doctorsList;
    },
    error:(error)=>{
      this.toastr.error(error);
    }
    });
  }
  }
  createBooking() {

  console.log("hiii")
      // takes value taken as input from form
    this.newBookingRequest = this.newBookingRequestForm.value;
    // takes in the doctorId value form the form so that newBookingRequest field can be set
    const selectedDoctor = this.newBookingRequestForm.get('doctorId')?.value;
    this.newBookingRequest.doctorId=selectedDoctor
    // takes in date-time from user then its converted to long timestamp to send to backend
    const selectedDate = new Date(this.newBookingRequestForm.value.bookingTime);
    const timestamp = selectedDate.getTime();
    this.newBookingRequest.bookingTime=timestamp;

    this.bookingService.createNewBooking(this.newBookingRequest).subscribe({
      next:(response:NewBookingResponse)=>{
        this.newBookingResponse=response;
      },
      error:(error)=>{
        console.log(error);
      }
    });
    this.newBookingRequestForm.reset;
  }



}
