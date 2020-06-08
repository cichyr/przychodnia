import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DoctorVisitListComponent } from './doctor-visit-list/doctor-visit-list.component';
import { VisitDetailsComponent } from './visit-details/visit-details.component';
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";
import { ReceptionistVisitListComponent } from './receptionist-visit-list/receptionist-visit-list.component';
import {BrowserModule} from "@angular/platform-browser";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AddVisitComponent } from './add-visit/add-visit.component';
import { AddPhysicalExamComponent } from './add-physical-exam/add-physical-exam.component';
import { AddLabExamComponent } from './add-lab-exam/add-lab-exam.component';
import { PhysicalExamDetailsComponent } from './physical-exam-details/physical-exam-details.component';
import { AddPatientComponent } from '../modals/add-patient/add-patient.component';

@NgModule({
  declarations: [DoctorVisitListComponent, VisitDetailsComponent, ReceptionistVisitListComponent, AddVisitComponent, AddPhysicalExamComponent, AddLabExamComponent, PhysicalExamDetailsComponent, AddPatientComponent],
    imports: [
        CommonModule,
        NgbDropdownModule,
        BrowserModule,
        NgbModule,
    ],
  exports: [
    AddPatientComponent,
    DoctorVisitListComponent,
    ReceptionistVisitListComponent
    ]
})
export class VisitModule { }
