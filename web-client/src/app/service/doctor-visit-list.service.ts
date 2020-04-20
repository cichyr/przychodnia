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

  uDet: UserDetails = {
    id: 0,
    city: "Lipnica",
    streetAddress1: "Sezamkowa 12/6",
    zipCode: "Zip",
    state: "State",
    contactNumber: "111 222 333"
  }

  doctor : Doctor = {
      id: 0,
      userDetailsId: this.uDet,
      firstName: "Jurek",
      lastName: "Doktor",
      licenseCode: "License for doctor"
    }

    patients: Patient[] = [
      {
        id: 0,
        userDetailsId: this.uDet,
        firstName: "Michal",
        lastName: "Grzdyl",
        peselNumber: "12345678910"
      },
      {
        id: 1,
        userDetailsId: this.uDet,
        firstName: "Jan",
        lastName: "Fryk",
        peselNumber: "Pesel"
      },
      {
        id: 2,
        userDetailsId: this.uDet,
        firstName: "Joanna",
        lastName: "Kalamarz",
        peselNumber: "Pesel"
      },
      {
        id: 3,
        userDetailsId: this.uDet,
        firstName: "Joanna",
        lastName: "Kalamarz",
        peselNumber: "Pesel"
      }
    ]

    recep: Receptionist[] = [
      {
        id: 0,
        userDetailsId: this.uDet,
        firstName: "First",
        lastName: " Receptionist",
        licenseCode: "License1"
      },
      {
        id: 1,
        userDetailsId: this.uDet,
        firstName: "Second",
        lastName: "Receptionist",
        licenseCode: "License2"
      }
    ]

    visStat: VisitState[] = [
      {
        id: 0,
        name: "W_TRAKCIE"
      },
      {
        id: 1,
        name: "ANULOWANA"
      },
      {
        id: 2,
        name: "ZAKONCZONA"
      }
    ]

  visits: Visit[] = [
    {
      id: 0,
      receptionistId: this.recep[0],
      doctorId: this.doctor,
      patientId: this.patients[0],
      stateId: this.visStat[2],
      description: "Description for first visit",
      diagnose: "Diagnose for first visit",
      registrationDate: "registration date for first visit",
      finalizationCancellationDate: "date for cancellation"
    },
    {
      id: 1,
      receptionistId: this.recep[0],
      doctorId: this.doctor,
      patientId: this.patients[1],
      stateId: this.visStat[1],
      description: "Description for first visit",
      diagnose: "Diagnose for second visit",
      registrationDate: "registration date for second visit",
      finalizationCancellationDate: "date for cancellation"
    },
    {
      id: 2,
      receptionistId: this.recep[1],
      doctorId: this.doctor,
      patientId: this.patients[3],
      stateId: this.visStat[2],
      description: "Description for third visit",
      diagnose: "Diagnose for third visit",
      registrationDate: "registration date for third visit",
      finalizationCancellationDate: "date for cancellation"
    },
    {
      id: 3,
      receptionistId: this.recep[1],
      doctorId: this.doctor,
      patientId: this.patients[2],
      stateId: this.visStat[1],
      description: "Description for visit",
      diagnose: "Diagnose for visit",
      registrationDate: "registration date for visit",
      finalizationCancellationDate: "date for cancellation"
    },
    {
      id: 4,
      receptionistId: this.recep[0],
      doctorId: this.doctor,
      patientId: this.patients[1],
      stateId: this.visStat[0],
      description: "Description for visit",
      diagnose: "Diagnose for visit",
      registrationDate: "registration date for visit",
      finalizationCancellationDate: "date for cancellation"
    }
  ]

  // Service functions
  //    TODO: Remake to db connections when backend is ready
  getAllVisitsForDoctor(): Visit[] {
    return this.visits;
  }
}
