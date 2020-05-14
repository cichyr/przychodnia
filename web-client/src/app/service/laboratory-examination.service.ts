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
import { LaboratoryExaminationGeneral } from '../data/examination/laboratory-examination-general';
import { Extractor } from '@angular/compiler';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class LaboratoryExaminationService {

  constructor(private http: HttpClient) { }

  // Service functions
  //    TODO: Remake to db connections when backend is ready
  getAllLaboratoryExams(): Observable<LaboratoryExaminationGeneral[]> {
    return this.http.get<LaboratoryExaminationGeneral[]>(`http://localhost:8080/api/laboratory_examinations`).pipe(
      map(labs => labs.map(labJson => new LaboratoryExaminationGeneral(
        labJson.id,
        labJson.name,
        labJson.creationDate,
        labJson.executionCancellationDate,
        labJson.approvalCancellationDate,
        labJson.status
      )))
    )
  }

  getLaboratoryExam(id: number): Observable<LaboratoryExamination> {
    //const labs = this.getAllLaboratoryExams()
    //for (let lab of labs) {
    //  if (lab.id == id) {
    //    return lab
    //  }
    //}
    return this.http.get<LaboratoryExamination>(`http://localhost:8080/api/laboratory_examinations/${id}/`)
  }

  // Changing status of examination. No backend means mockup function
  changeExaminationStatus(status: string, examination: LaboratoryExamination): void {
    

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
