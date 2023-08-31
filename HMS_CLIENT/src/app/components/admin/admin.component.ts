import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUserService } from 'src/app/services/login-user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  constructor(private router: Router,private loginService:LoginUserService,private toastr: ToastrService) {}

  logout(){
    this.loginService.logout();
    this.toastr.success('Logged Out', 'Success');
    this.router.navigate(["emh"]);
  }
  
  
  
}
