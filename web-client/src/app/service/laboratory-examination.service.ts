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
import { Extractor } from '@angular/compiler';


@Injectable({
  providedIn: 'root'
})
export class LaboratoryExaminationService {

  d0: Date = new Date("1998-02-19")
  d1: Date = new Date("1995-12-15")
  d2: Date = new Date("2020-02-19")
  d3: Date = new Date("2019-06-20")
  d4: Date = new Date("2019-06-21")
  d5: Date = new Date("2020-03-16")
  constructor(private http: HttpClient) { }

  private static readonly HOSTNAME = 'http://localhost:8080';

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
    receptionist: this.rec,
    doctor: this.doc,
    patient: this.pat,
    state: this.state,
    description: "Desc",
    diagnose: "Diag",
    registrationDate: "registration",
    finalizationCancellationDate: this.d0
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
      executionCancellationDate: this.d1,
      approvalCancellationDate: this.d1,
      formatDate: LaboratoryExamination.prototype.formatDate
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
      executionCancellationDate: this.d2,
      approvalCancellationDate: this.d2,
      formatDate: LaboratoryExamination.prototype.formatDate
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
      executionCancellationDate: this.d3,
      approvalCancellationDate: this.d3,
      formatDate: LaboratoryExamination.prototype.formatDate
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
      executionCancellationDate: this.d4,
      approvalCancellationDate: this.d4,
      formatDate: LaboratoryExamination.prototype.formatDate
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
      executionCancellationDate: this.d5,
      approvalCancellationDate: this.d5,
      formatDate: LaboratoryExamination.prototype.formatDate
    }
  ]

  // Service functions
  //    TODO: Remake to db connections when backend is ready
  getAllLaboratoryExams(): LaboratoryExamination[] {
    return this.laboratories
    //return this.http.get(LaboratoryExaminationService.HOSTNAME + '/api/laboratory_examinations')
  }

  getLaboratoryExam(id: number): LaboratoryExamination {
    const labs = this.getAllLaboratoryExams()
    for (let lab of labs) {
      if (lab.id == id) {
        return lab
      }
    }
    //return this.http.get(LaboratoryExaminationService.HOSTNAME + '/api/laboratory_examinations/' + id)
  }

  // Changing status of examination. No backend means mockup function
  changeExaminationStatus(status: string, examination: LaboratoryExamination): void {
    switch (status) {
      case 'Done':
        examination.stateId = this.es_wyk
        break

      case 'CanWork':
        examination.stateId = this.es_al
        break

      case 'Approve':
        examination.stateId = this.es_zat
        break

      case 'CanSup':
        examination.stateId = this.es_ak
        break
    }

    // put to DataBase
  }

  changeExaminationResultNote(type: string, input: string, examination: LaboratoryExamination): void {
    switch (type) {
      case 'result':
        examination.result = input
        break

      case 'note':
        examination.supervisorNote = input
        break
    }

    // put to DataBase
  }
}
