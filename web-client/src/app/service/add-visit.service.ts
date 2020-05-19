import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from '../data/patient/patient';
import { HttpClient } from '@angular/common/http';
import { Doctor } from '../data/doctor/doctor';
import { ExaminationDictionary } from '../data/examination/examination-dictionary';
import { VisitDetails } from '../data/visit/visit-details';
import { VisitToAdd } from '../data/visit/visit-to-add';

@Injectable({
  providedIn: 'root'
})
export class AddVisitService {

  constructor(private http: HttpClient) { }

  //  http://localhost:8080/api/doctors/?first_name=B&last_name=A
  getPatients(patient?: Patient): Observable<Patient[]> {
    if(patient!==undefined) //Czy przekazano argument
    {
      let useAnd=false
      let question=""
      if(patient.firstName!==""||patient.lastName!==""||patient.peselNumber!=="") //Czy któryś jest nie pusty
      {
        if(patient.firstName!=="")
        {
          question+="first_name="+patient.firstName;
          useAnd=true
        }
        if(patient.lastName!=="")
        {
          if(useAnd) { question+="&" }
          question+="last_name="+patient.lastName;
          useAnd=true
        }
        if(patient.peselNumber!=="")
        {
          if(useAnd) { question+="&" }
          question+="pesel_number="+patient.peselNumber;
        }
        return this.http.get<Patient[]>(`http://localhost:8080/api/patients/?${question}`)
      }
      return this.http.get<Patient[]>(`http://localhost:8080/api/patients/`)
    }else{
      return this.http.get<Patient[]>(`http://localhost:8080/api/patients/`)
    }
  }

  getDoctors(doctor?: Doctor): Observable<Doctor[]> {
    if(doctor!==undefined) //Czy przekazano argument
    {
      let useAnd=false
      let question=""
      if(doctor.firstName!==""||doctor.lastName!==""||doctor.licenseCode!=="") //Czy któryś jest nie pusty
      {
        if(doctor.firstName!=="")
        {
          question+="first_name="+doctor.firstName;
          useAnd=true
        }
        if(doctor.lastName!=="")
        {
          if(useAnd) { question+="&" }
          question+="last_name="+doctor.lastName;
          useAnd=true
        }
        if(doctor.licenseCode!=="")
        {
          if(useAnd) { question+="&" }
          question+="pesel_number="+doctor.licenseCode;
        }
        return this.http.get<Doctor[]>(`http://localhost:8080/api/doctors/?${question}`)
      }
      return this.http.get<Doctor[]>(`http://localhost:8080/api/doctors/`)
    }else{
      return this.http.get<Doctor[]>(`http://localhost:8080/api/doctors/`)
    }
  }

  //http://localhost:8080/api/possible_examinations/?examination_type=laboratory
  //Możliwe badania fizykalne
  getPossiblePhysicalExamination(): Observable<ExaminationDictionary[]> {
    return this.http.get<ExaminationDictionary[]>(`http://localhost:8080/api/possible_examinations/?examination_type=physical`)
  }
//http://localhost:8080/api/possible_examinations/?examination_type=laboratory
  //Możliwe badania fizykalne
  getPossibleLabExamination(): Observable<ExaminationDictionary[]> {
    return this.http.get<ExaminationDictionary[]>(`http://localhost:8080/api/possible_examinations/?examination_type=laboratory`)
  }

  postVisit(newVisit: VisitToAdd): Observable<VisitDetails>{
    return this.http.post<VisitDetails>(`http://localhost:8080/api/visits`,newVisit)
  }
}
