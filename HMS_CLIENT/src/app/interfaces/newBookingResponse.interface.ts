import { DoctorSpecialization } from "../enums/DoctorSpecialization.enum";

export interface NewBookingResponse{
    bookingId:number;
    patientName:string;
    patientAge:number;
    doctorName:string;
    bookingTime:number;
    doctorSpecialization:DoctorSpecialization;
}