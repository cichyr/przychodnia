import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DoctorVisitListComponent } from './doctor-visit-list/doctor-visit-list.component';



@NgModule({
  declarations: [DoctorVisitListComponent],
  imports: [
    CommonModule
  ],
  exports: [
    DoctorVisitListComponent
  ]
})
export class VisitModule { }
