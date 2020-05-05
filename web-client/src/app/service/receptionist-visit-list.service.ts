import { Injectable } from '@angular/core';
import {Receptionist} from "../data/receptionist/receptionist";
import {Observable} from "rxjs";
import {ReceptionistVisit} from "../data/visit/receptionist-visit";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ReceptionistVisitListService {

  constructor(private http : HttpClient) { }

  getVisits(): Observable<ReceptionistVisit[]> {
    return this.http.get<ReceptionistVisit[]>('http://localhost:8080/api/receptionists/1/visits')
  }

}
