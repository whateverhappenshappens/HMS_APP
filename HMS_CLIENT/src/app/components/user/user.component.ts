import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AccountService } from 'src/app/services/account.service';
import { LoginUserService } from 'src/app/services/login-user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  constructor(private router: Router,private loginService:LoginUserService,private accountService :AccountService) {}

  logout(){
    this.loginService.logout();
    this.router.navigate(["emh"]);
  }
  deactivateUserAccount() {
    const userId=Number(sessionStorage.getItem("UID"))
    this.accountService.deactivateUserAccount(userId).subscribe({
      next:(response:boolean)=>{
        if(response==true){
          console.log("deactivated");
        }
      },error:(error)=>{
        console.log("couldnt deactivate account",error);
      }
    });
    }
   


}
