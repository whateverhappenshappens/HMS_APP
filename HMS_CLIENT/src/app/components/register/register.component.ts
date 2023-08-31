import { Component,OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators,FormsModule } from '@angular/forms';
import { NewUserRequest } from 'src/app/interfaces/newUserRequest.interface';
import { Gender } from 'src/app/enums/gender.enum';
import { RegisterUserService } from 'src/app/services/register-user.service';
import { NewUserResponse } from 'src/app/interfaces/NewUserResponse.interface';
import { ToastrModule, ToastrService } from 'ngx-toastr';

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
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
 
  newUserRegistrationForm!: FormGroup;
  genders = Object.values(Gender);
  showRegisterToLinkButton:boolean=false; 
  constructor(private fb:FormBuilder,private registerUserService:RegisterUserService,private toastr:ToastrService){}
  ngOnInit(): void {
    this.newUserRegistrationForm = this.fb.group({
      name: ['', [Validators.required,alphabetsOnlyValidator()]],
      age: ['', [Validators.required,validAge()]],
      email: ['', [Validators.required, Validators.email]],
      gender: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      username: ['', Validators.required],
    });
  }
  onSubmit() {
      const newUser:NewUserRequest = this.newUserRegistrationForm!.value;
      console.log(newUser);      
      // used to get and store any navigator related information and store it in the attr
      const clientInfo={
       language:this.getBrowserLanguage()
      }
      newUser.attr = [JSON.stringify(clientInfo)];
      newUser.attr=[];
      console.log(newUser);
      this.registerUserService.registerUser(newUser).subscribe({
        next: (newUserResponse:NewUserResponse)=> {
          this.toastr.success('Registration successful!', 'Success');
          console.log('Registration Successfull:',newUserResponse);
          this.newUserRegistrationForm.reset;
          this.showRegisterToLinkButton=true;
        },
        error: (error: any) => {
          this.toastr.error('Registration failed!', error);
          console.log('Registration Failed',error);
        }
      });
      this.newUserRegistrationForm.reset;
}
  private getBrowserLanguage():string{
    return "Hello";
  }
}
