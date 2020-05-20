import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PhysicalExamination } from '../data/examination/physical-examination';

@Injectable({
  providedIn: 'root'
})
export class PhysicalExaminationDetailsService {

  constructor(private http: HttpClient) { }

  getPhysicalExamination(id: number): Observable<PhysicalExamination> {
    return this.http.get<PhysicalExamination>(`http://localhost:8080/api/physical_examinations/${id}`);
  }
}
