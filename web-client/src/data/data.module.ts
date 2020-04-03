import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Doctor} from './doctor/doctor'
import {LaboratoryExamination} from './examination/laboratory-examination'
import {PhysicalExamination} from './examination/physical-examination'
import {LabSupervisor} from './labSupervisor/lab-supervisor'
import {LabWorker} from './labWorker/lab-worker'
import {Patient} from './patient/patient'
import {Receptionist} from './receptionist/receptionist'
import {UserDetails} from './userDetails/user-details'
import {Visit} from './visit/visit'


@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  exports: [
    Doctor,
    LaboratoryExamination,
    PhysicalExamination,
    LabSupervisor,
    LabWorker,
    Patient,
    Receptionist,
    UserDetails,
    Visit
  ]
})
export class DataModule { }
