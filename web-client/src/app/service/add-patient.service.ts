import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NewPatient } from '../data/patient/new-patient';

@Injectable({
  providedIn: 'root'
})
export class AddPatientService {

  constructor(private http: HttpClient) { }

  postPatient(newPatient: NewPatient): Observable<number>{
    return this.http.post<number>(`http://localhost:8080/api/patients`,newPatient)
  }
}
