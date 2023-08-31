import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { NewUserRequest } from 'src/app/interfaces/newUserRequest.interface';
import { Gender } from 'src/app/enums/gender.enum';
import { RegisterUserService } from 'src/app/services/register-user.service';
import { NewUserResponse } from 'src/app/interfaces/NewUserResponse.interface';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { LoginUserService } from 'src/app/services/login-user.service';
import { Router } from '@angular/router';
import { LoginResponse } from 'src/app/interfaces/LoginResponse.interface';
import { UserRoleResponse } from 'src/app/interfaces/UserRoleResonse.interface';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {



  userLoginForm!: FormGroup;
showRegisterToLinkButton: any;
  constructor(private fb: FormBuilder, private loginUserService: LoginUserService, private toastr: ToastrService, private router: Router) { }
  ngOnInit(): void {
    this.userLoginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }
  // this happens when we submit the form
  loginOnSubmit() {
    const credentials= this.userLoginForm!.value;
    const username = credentials.username;
    const password = credentials.password;
    this.loginUserService.login(username,password).subscribe(
      {
        next: (response:LoginResponse)=> {
          sessionStorage.setItem("token",response.token);
          console.log(sessionStorage.getItem('token'));
          this.toastr.success("Login Successfull!")
          this.navigateUser();
        },
        error: (error) => {
          this.toastr.error('check credentials!');
          this.toastr.error('Login failed!');
          this.userLoginForm.reset();
          console.log('Login Failed',error);
        }
      }
     ); 
    }
    navigateUser(){
      this.loginUserService.getUserRoleFromToken().subscribe({
      next:(userRole:UserRoleResponse) =>{
        if(userRole.role === "ADMIN"){
          this.router.navigate(["/admin-dashboard"])
        }
        else if(userRole.role === "USER"){
          this.router.navigate(["/user-dashboard"])
        }
        else if(userRole.role === "DOCTOR"){
          this.router.navigate(["/doctor-dashboard"])
        }
      },
      error: (error: { status: number; }) => {
        console.log(error);
        if(error.status === 401){
          console.log("not authorised");
          this.toastr.error("not authorised");
          if(sessionStorage.getItem("token")!==null){
            sessionStorage.removeItem("token");
            this.router.navigate(["/login"]);
            this.userLoginForm.reset();
          }
        }
      }
    })
  }
  }