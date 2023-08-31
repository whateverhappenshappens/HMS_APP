import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NewUserRequest } from '../interfaces/newUserRequest.interface';
import { NewUserResponse } from '../interfaces/NewUserResponse.interface';


@Injectable({
  providedIn: 'root'
})
export class RegisterUserService {
  private apiUrl = "http://localhost:8080/hms/";

  constructor(private http: HttpClient) {}

  registerUser(user: NewUserRequest): Observable<NewUserResponse> {
  return this.http.post<NewUserResponse>(this.apiUrl+"user/registerUser",user);
  }
}
