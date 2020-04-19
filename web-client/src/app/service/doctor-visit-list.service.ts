import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Doctor} from '../data/doctor/doctor';
import {UserDetails} from '../data/userDetails/user-details';
import { Patient } from '../data/patient/patient';
import { Receptionist } from '../data/receptionist/receptionist';
import { VisitState } from '../data/visit/visit-state';
import { Visit } from '../data/visit/visit';
import { LabSupervisor } from '../data/labSupervisor/lab-supervisor';
import { LabWorker } from '../data/labWorker/lab-worker';
import { ExaminationDictionary, examinationType } from '../data/examination/examination-dictionary';
import { ExaminationState } from '../data/examination/examination-state';
import { LaboratoryExamination } from '../data/examination/laboratory-examination';

@Injectable({
  providedIn: 'root'
})
export class DoctorVisitListService {

  constructor(private http: HttpClient) { }

  //Dummy mock objects, used until there is no backend
  //TODO: Remove when backend is created


}
