import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

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

  // Getting Laboratory Examinations list
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

  // Getting Laboratory Examination details
  getLaboratoryExam(id: number): Observable<LaboratoryExamination> {
    return this.http.get<LaboratoryExamination>(`http://localhost:8080/api/laboratory_examinations/${id}/`).pipe(
      map(labJson => new LaboratoryExamination(
        labJson.id,
        labJson.name,
        labJson.status,
        labJson.labWorker,
        labJson.labSupervisor,
        labJson.result,
        labJson.doctorNote,
        labJson.supervisorNote,
        labJson.creationDate,
        labJson.patient,
        labJson.doctor,
        labJson.executionCancellationDate,
        labJson.approvalCancellationDate
      ))
    )
  }

  // Changing status of examination
  changeExaminationStatus(status: string, id: number): Observable<LaboratoryExamination> {
    switch(status)
    {
      case 'Done':
        return this.http.put<LaboratoryExamination>(`http://localhost:8080/api/laboratory_examinations/${id}/approve_laborant`, null)

      case 'CanWork':
        return this.http.put<LaboratoryExamination>(`http://localhost:8080/api/laboratory_examinations/${id}/cancel_laborant`, null)

      case 'Approve':
        return this.http.put<LaboratoryExamination>(`http://localhost:8080/api/laboratory_examinations/${id}/approve_supervisor`, null)

      case 'CanSup':
        return this.http.put<LaboratoryExamination>(`http://localhost:8080/api/laboratory_examinations/${id}/cancel_supervisor`, null)
    }
  }

  // Changing examination Result/SupervisorNote
  changeExaminationResultNote(type: string, input: string, id: number): Observable<LaboratoryExamination> {
    switch (type) {
      case 'result':
        return this.http.put<LaboratoryExamination>(`http://localhost:8080/api/laboratory_examinations/${id}/result`,{
          "result": input
        })
        break

      case 'note':
        return this.http.put<LaboratoryExamination>(`http://localhost:8080/api/laboratory_examinations/${id}/supervisor_note`,{
          "result": input
        })
        break
    }
  }
}
