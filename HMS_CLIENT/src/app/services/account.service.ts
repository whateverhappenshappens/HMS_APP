import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
 

  constructor(private http: HttpClient) { }
  private baseUrl = 'http://localhost:8080/hms/'
  deactivateUserAccount(userId:number):Observable<boolean>{
    const headers = new HttpHeaders({
      'xHmAuthToken': sessionStorage.getItem('token') ?? ''
      });
      const url = `${this.baseUrl}user/${userId}/deactivate-user`;
      var result= this.http.put<boolean>(url,userId,{headers}) 
      console.log(result);
      return result;
  }
  reactivateAccount(userId:number):Observable<boolean>{
    const headers = new HttpHeaders({
      'xHmAuthToken': sessionStorage.getItem('token') ?? ''
      });
      const url = `${this.baseUrl}user/${userId}/reactivate-user`;
      var result= this.http.put<boolean>(url,userId,{headers}) 
      console.log(result);
      return result;
  }
  blockUserAccount(userId:number):Observable<boolean>{
    const headers = new HttpHeaders({
      'xHmAuthToken': sessionStorage.getItem('token') ?? ''
      });
      const url = `${this.baseUrl}user/${userId}/block-user`;
      var result= this.http.put<boolean>(url,userId,{headers}) 
      console.log(result);
      return result;
  }
}
