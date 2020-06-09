import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ReceptionistVisit} from "../data/visit/receptionist-visit";
import {HttpClient} from "@angular/common/http";
import {Visit} from "../data/visit/visit";
import {VisitDetails} from "../data/visit/visit-details";

@Injectable({
  providedIn: 'root'
})
export class ReceptionistVisitListService {

  constructor(private http : HttpClient) { }

  getVisits(id: number): Observable<ReceptionistVisit[]> {
    return this.http.get<ReceptionistVisit[]>(`http://localhost:8080/api/receptionists/${id}/visits`)
  }

  cancelVisit(id: number): Observable<VisitDetails> {
    return this.http.put<VisitDetails>(`http://localhost:8080/api/visits/${id}/cancel`, null);
  }

  mapVisitToReceptionistVisit(visit: VisitDetails): ReceptionistVisit{
   return new ReceptionistVisit(visit.visitId, visit.patient.firstName, visit.patient.lastName, visit.patient.peselNumber,
      visit.doctor.firstName, visit.doctor.lastName, visit.state, new Date(visit.registrationDate.toString()),
      new Date(visit.finalizationCancellationDate.toString()), new Date(visit.appointmentDateTime.toString()));
  }
}
