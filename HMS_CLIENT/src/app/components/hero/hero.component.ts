import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit {
  constructor(private toastr:ToastrService,private doctorService:DoctorService){}
  ngOnInit(): void {
    this.toastr.info('Welcome to Eisler Memorial Hospital');
  }

}
