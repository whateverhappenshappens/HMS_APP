import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewBookingResponse } from '../interfaces/newBookingResponse.interface';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class NewBookingService {
  private apiUrl = "http://localhost:8080/hms/";

  constructor(private http: HttpClient) {}

  


}
