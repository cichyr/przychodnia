import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {LaboratoryExamination} from '../data/examination/laboratory-examination';
import {formatDate} from "@angular/common";
import {Observable, pipe} from "rxjs";
import {DoctorVisit} from "../data/visit/doctor-visit";
import {map, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})

export class DoctorVisitListService {

  constructor(private http: HttpClient) {
  }

  getVisits(): Observable<DoctorVisit[]> {
    return this.http.get<DoctorVisit[]>('http://localhost:8080/api/doctors/3/visits')
  }

}
