import { Component, NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AddBookingComponent } from "./add-booking/add-booking.component";
import { AllBookingByUserIdComponent } from "./all-booking-by-user-id/all-booking-by-user-id.component";
import { UserDetailsByIdComponent } from "./user-details-by-id/user-details-by-id.component";
import { BookingDetailComponent } from "./booking-detail/booking-detail.component";
import { AllBookingByUserComponent } from "./all-booking-by-user/all-booking-by-user.component";
import { ReactivateUserAccountComponent } from "./reactivate-user-account/reactivate-user-account.component";

const routes:Routes = [
    { path: 'add-booking', component: AddBookingComponent },
    {path: 'all-booking-by-id',component:AllBookingByUserIdComponent},
    {path:'all-user-details-by-id',component:UserDetailsByIdComponent},
    {path:'booking-details',component:BookingDetailComponent},
    {path:'all-booking-by-user',component:AllBookingByUserComponent},
    {path:'reactivate-user',component:ReactivateUserAccountComponent}

]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })

export class BookingRoutingModule{}