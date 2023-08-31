import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../interfaces/Doctor.interface';
@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private apiUrl = "http://localhost:8080/hms/";
  constructor(private http:HttpClient){}

  getDoctorsBySpecialization(specialization:string):Observable<Doctor[]>{
    const url = `${this.apiUrl}all-doctors/${specialization}`;
    console.log("yaha tak aaya");
    var ff= this.http.get<Doctor[]>(url);
    console.log(ff);
    console.log(typeof(ff));
    return ff;
  }
  
}
