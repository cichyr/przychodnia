import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ReceptionistVisit} from "../data/visit/receptionist-visit";
import {HttpClient} from "@angular/common/http";
import {Visit} from "../data/visit/visit";

@Injectable({
  providedIn: 'root'
})
export class ReceptionistVisitListService {

  constructor(private http : HttpClient) { }

  getVisits(id: number): Observable<ReceptionistVisit[]> {
    return this.http.get<ReceptionistVisit[]>(`http://localhost:8080/api/receptionists/${id}/visits`)
  }

  cancelVisit(id: number): Observable<Visit> {

    console.log('Visit cancelled, id: ' + id);
    return this.http.put<Visit>(`http://localhost:8080/api/visits/${id}/cancel`, null);
  }
}
