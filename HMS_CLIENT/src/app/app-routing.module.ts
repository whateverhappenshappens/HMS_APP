import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeroComponent } from './components/hero/hero.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdminComponent } from './components/admin/admin.component';
import { DoctorComponent } from './components/doctor/doctor.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  {path:"emh",component:HeroComponent},
  {path:'',redirectTo:'emh',pathMatch:"full"},
  {path:"login",component:LoginComponent},
  {path:"register",component:RegisterComponent},
  {path: 'admin-dashboard',component:AdminComponent},
  {path: 'booking', loadChildren: () => import('./module/booking/booking.module').then(m => m.BookingModule) },
  {path:"doctor-dashboard",component:DoctorComponent},
  {path:"user-dashboard",component:UserComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
