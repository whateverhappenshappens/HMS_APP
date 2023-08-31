import { Component } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { DoctorService } from 'src/app/services/doctor.service';
import { LoginUserService } from 'src/app/services/login-user.service';
import { LoginComponent } from '../login/login.component';
import { ToastrService } from 'ngx-toastr';
import { Router, RouterModule } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {


  constructor(private loginService: LoginComponent,private toastr:ToastrService,private router:Router) { }
  logout() {
    this.loginService.loginOnSubmit();
    this.toastr.success()
    this.router.navigate(["emh"]);
    console.log("heelo");
  }
}
