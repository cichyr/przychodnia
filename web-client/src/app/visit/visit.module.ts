import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DoctorVisitListComponent } from './doctor-visit-list/doctor-visit-list.component';
import { VisitDetailsComponent } from './visit-details/visit-details.component';
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";
import { ReceptionistVisitListComponent } from './receptionist-visit-list/receptionist-visit-list.component';
import {BrowserModule} from "@angular/platform-browser";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [DoctorVisitListComponent, VisitDetailsComponent, ReceptionistVisitListComponent],
    imports: [
        CommonModule,
        NgbDropdownModule,
        BrowserModule,
        NgbModule,
    ],
  exports: [
    DoctorVisitListComponent,
    ReceptionistVisitListComponent
    ]
})
export class VisitModule { }
