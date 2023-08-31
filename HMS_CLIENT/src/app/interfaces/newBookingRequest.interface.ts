import { DoctorSpecialization } from "../enums/DoctorSpecialization.enum";
import { Gender } from "../enums/gender.enum";

export interface NewBookingRequest{
    patientAge:number
    patientName:string
    gender:Gender
    bookingTime:number
    doctorId:number
}