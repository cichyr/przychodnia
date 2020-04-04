import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { Doctor } from "../data/doctor/doctor";
import { UserDetails } from '../data/userDetails/user-details';
import { Patient } from '../data/patient/patient';
import { Receptionist } from '../data/receptionist/receptionist';
import { VisitState } from '../data/visit/visit-state';
import { Visit } from '../data/visit/visit';
import { LabSupervisor } from '../data/labSupervisor/lab-supervisor';
import { LabWorker } from '../data/labWorker/lab-worker';
import { ExaminationDictionary, examinationType } from '../data/examination/examination-dictionary';
import { ExaminationState } from '../data/examination/examination-state';
import { LaboratoryExamination } from '../data/examination/laboratory-examination';


@Injectable({
  providedIn: 'root'
})
export class LaboratoryExaminationService {

  constructor(private http: HttpClient) { }

  // Dummy mock objects, used until there is no backend
  //    TODO: Remove when backend is created
  uDet: UserDetails = {
    id: 0,
    city: "City",
    streetAddress1: "Street",
    zipCode: "Zip",
    state: "State",
    contactNumber: "Number"
  }

  pat: Patient = {
    id: 0,
    userDetailsId: this.uDet,
    firstName: "Example",
    lastName: "Patient",
    peselNumber: "Pesel"
  }

  rec: Receptionist = {
    id: 0,
    userDetailsId: this.uDet,
    firstName: "Example",
    lastName: "Receptionist",
    licenseCode: "License"
  }

  state: VisitState = {
    id: 0,
    name: "Status"
  }

  doc: Doctor = {
    id: 0,
    userDetailsId: this.uDet,
    firstName: "Example",
    lastName: "Doctor",
    licenseCode: "License"
  }

  visit: Visit = {
    id: 0,
    receptionistId: this.rec,
    doctorId: this.doc,
    patientId: this.pat,
    stateId: this.state,
    description: "Desc",
    diagnose: "Diag",
    registrationDate: "registration",
    finalizationCancellationDate: "visit date"
  }

  lw: LabWorker = {
    id: 0,
    userDetailsId: this.uDet,
    firstName: "Example",
    lastName: "Worker",
    licenseCode: "License"
  }

  ls: LabSupervisor = {
    id: 0,
    userDetailsId: this.uDet,
    firstName: "Example",
    lastName: "Supervisor",
    licenseCode: "License"
  }

  ed: ExaminationDictionary = {
    id: 0,
    name: "dict",
    type: examinationType.Laboratory
  }

  es_zle: ExaminationState = {
    id: 0,
    name: "ZLE"
  }

  es_wyk: ExaminationState = {
    id: 1,
    name: "WYK"
  }

  es_al: ExaminationState = {
    id: 2,
    name: "ANUL_LAB"
  }

  es_zat: ExaminationState = {
    id: 3,
    name: "ZATW"
  }

  es_ak: ExaminationState = {
    id: 4,
    name: "ANUL_KLAB"
  }

  laboratories: LaboratoryExamination[] = [
    {
      id: 0,
      examinationId: this.ed,
      visitId: this.visit,
      stateId: this.es_wyk,
      labWorkerId: this.lw,
      labSupervisorId: this.ls,
      result: "result",
      doctorNote: "Rentgen oka",
      supervisorNote: "supervisor note",
      executionCancellationDate: "exec date",
      approvalCancellationDate: "app date"
    },
    {
      id: 1,
      examinationId: this.ed,
      visitId: this.visit,
      stateId: this.es_zat,
      labWorkerId: this.lw,
      labSupervisorId: this.ls,
      result: "result",
      doctorNote: "Badanie moczu",
      supervisorNote: "supervisor note",
      executionCancellationDate: "exec 2 date",
      approvalCancellationDate: "app 2 date"
    },
    {
      id: 2,
      examinationId: this.ed,
      visitId: this.visit,
      stateId: this.es_ak,
      labWorkerId: this.lw,
      labSupervisorId: this.ls,
      result: "result",
      doctorNote: "Badanie krwi pod kątem SARS-CoV-2",
      supervisorNote: "supervisor note",
      executionCancellationDate: "exec 3 date",
      approvalCancellationDate: "app 3 date"
    },
    {
      id: 345345,
      examinationId: this.ed,
      visitId: this.visit,
      stateId: this.es_al,
      labWorkerId: this.lw,
      labSupervisorId: this.ls,
      result: "result",
      doctorNote: "Rentgen czaszki",
      supervisorNote: "supervisor note",
      executionCancellationDate: "exec 3 date",
      approvalCancellationDate: "app 3 date"
    },
    {
      id: 465465465,
      examinationId: this.ed,
      visitId: this.visit,
      stateId: this.es_zle,
      labWorkerId: this.lw,
      labSupervisorId: this.ls,
      result: "result",
      doctorNote: "doctor note 3",
      supervisorNote: "supervisor note",
      executionCancellationDate: "exec 3 date",
      approvalCancellationDate: "app 3 date"
    }
  ]

  // Service functions
  //    TODO: Remake to db connections when backend is ready
  getAllLaboratoryExams(): LaboratoryExamination[] {
    return this.laboratories
  }
}
