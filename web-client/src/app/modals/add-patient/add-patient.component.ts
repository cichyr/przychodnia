import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { AddPatientService } from 'src/app/service/add-patient.service';
import { NewPatient } from 'src/app/data/patient/new-patient';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent implements OnInit {

  patient: NewPatient = new NewPatient()
  patientId: number

  addPatientSub: Subscription

  constructor(public activeModal: NgbActiveModal, private addPatientService: AddPatientService){ }

  ngOnInit(): void {
  }

  //powrót do dodawania wizyty
  confirm() {
    this.addPatientSub = this.addPatientService.postPatient(this.patient).subscribe(_patientId => {
      this.patientId = _patientId
      this.activeModal.close(this.patientId)
    })
  }

}
