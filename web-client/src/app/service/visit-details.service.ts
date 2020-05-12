import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {VisitDetails} from "../data/visit/visit-details";

@Injectable({
  providedIn: 'root'
})
export class VisitDetailsService {

  constructor(private http: HttpClient) { }

  getVisit(id: number): Observable<VisitDetails> {
    return this.http.get<VisitDetails>(`http://localhost:8080/api/visits/${id}`);
  }

  finalizeVisit(id: number): Observable<VisitDetails> {
    return this.http.put<VisitDetails>(`http://localhost:8080/api/visits/${id}/finalize`, null);
  }

  cancelVisit(id: number): Observable<VisitDetails> {
    return this.http.put<VisitDetails>(`http://localhost:8080/api/visits/${id}/cancel`, null);
  }

  setInterview(id: number, description: String, diagnose: String): Observable<VisitDetails> {
    return this.http.put<VisitDetails>(`http://localhost:8080/api/visits/${id}/interview`,
      {
              "description": description,
              "diagnose": diagnose
            }
      );
  }

}
