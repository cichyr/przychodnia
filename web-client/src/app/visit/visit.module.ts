import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DoctorVisitListComponent } from './doctor-visit-list/doctor-visit-list.component';
import { VisitDetailsComponent } from './visit-details/visit-details.component';
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  declarations: [DoctorVisitListComponent, VisitDetailsComponent],
    imports: [
        CommonModule,
        NgbDropdownModule
    ],
  exports: [
    DoctorVisitListComponent
  ]
})
export class VisitModule { }
