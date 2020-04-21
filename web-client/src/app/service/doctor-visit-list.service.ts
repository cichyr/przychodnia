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
      lastName: "Doktorek",
      licenseCode: "License for doctor"
    }

    patients: Patient[] = [
      {
        id: 0,
        userDetailsId: this.uDet,
        firstName: "Michał",
        lastName: "Grzdyl",
        peselNumber: "12345678910"
      },
      {
        id: 1,
        userDetailsId: this.uDet,
        firstName: "Jan",
        lastName: "Fryk",
        peselNumber: "09876543211"
      },
      {
        id: 2,
        userDetailsId: this.uDet,
        firstName: "Joanna",
        lastName: "Kałamarz",
        peselNumber: "13456789025"
      },
      {
        id: 3,
        userDetailsId: this.uDet,
        firstName: "Anna",
        lastName: "Rabiej",
        peselNumber: "01928374655"
      }
    ]

    recep: Receptionist[] = [
      {
        id: 0,
        userDetailsId: this.uDet,
        firstName: "Mirosława",
        lastName: "Wicek",
        licenseCode: "License1"
      },
      {
        id: 1,
        userDetailsId: this.uDet,
        firstName: "Dorota",
        lastName: "Gruzek",
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
      registrationDate: "01.01.2020",
      finalizationCancellationDate: "05.01.2020"
    },
    {
      id: 1,
      receptionistId: this.recep[0],
      doctorId: this.doctor,
      patientId: this.patients[1],
      stateId: this.visStat[1],
      description: "Description for first visit",
      diagnose: "Diagnose for second visit",
      registrationDate: "09.09.2019",
      finalizationCancellationDate: "11.09.2019"
    },
    {
      id: 2,
      receptionistId: this.recep[1],
      doctorId: this.doctor,
      patientId: this.patients[3],
      stateId: this.visStat[2],
      description: "Description for third visit",
      diagnose: "Diagnose for third visit",
      registrationDate: "12.05.2020",
      finalizationCancellationDate: "10.06.2020"
    },
    {
      id: 3,
      receptionistId: this.recep[1],
      doctorId: this.doctor,
      patientId: this.patients[2],
      stateId: this.visStat[1],
      description: "Description for visit",
      diagnose: "Diagnose for visit",
      registrationDate: "25.08.2020",
      finalizationCancellationDate: "18.09.2020"
    },
    {
      id: 4,
      receptionistId: this.recep[0],
      doctorId: this.doctor,
      patientId: this.patients[1],
      stateId: this.visStat[0],
      description: "Description for visit",
      diagnose: "Diagnose for visit",
      registrationDate: "09.07.2020",
      finalizationCancellationDate: "date for cancellation"
    }
  ]

  // Service functions
  //    TODO: Remake to db connections when backend is ready
  getAllVisitsForDoctor(): Visit[] {
    return this.visits;
  }
}
