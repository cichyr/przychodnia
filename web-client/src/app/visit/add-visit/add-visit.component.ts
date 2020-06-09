import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/data/patient/patient';
import { Router, ActivatedRoute } from '@angular/router';
import { Doctor } from 'src/app/data/doctor/doctor';
import { AddVisitService } from 'src/app/service/add-visit.service';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { VisitToAdd } from 'src/app/data/visit/visit-to-add';
import { VisitDetails } from 'src/app/data/visit/visit-details';
import { NgbModal, NgbDatepicker, NgbDateStruct, NgbCalendar, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';
import { AddPatientComponent } from 'src/app/modals/add-patient/add-patient.component';


@Component({
  selector: 'app-add-visit',
  templateUrl: './add-visit.component.html',
  styleUrls: ['./add-visit.component.css'],
})
export class AddVisitComponent implements OnInit {
  patientList: Patient[]
  selectedPatient: Patient = new Patient()
  patientSought: Patient = new Patient()
  patientSub: Subscription
  
  doctorList: Doctor[]
  selectedDoctor: Doctor = new Doctor()
  doctorSought: Doctor = new Doctor()
  doctorSub: Subscription

  model: NgbDateStruct
  time: NgbTimeStruct = {hour: 13, minute: 30, second: 0};
  fulldate: Date;
  appointmentDateTime: string
  

  visitToAdd: VisitToAdd = new VisitToAdd()
  visitDetails: VisitDetails
  visitDetailsSub: Subscription

  buttonConfirm = false

  constructor(private router: Router, private userService: UserService, private addVisitService: AddVisitService,private modalService: NgbModal,private calendar: NgbCalendar) { }
  stage = 1;

  ngOnInit(): void {
    //Ustawienie numeru recepcjonisty
    this.visitToAdd.receptionistId = this.userService.getId();

    //Wczytanie listy pacjentów
    this.patientSub = this.addVisitService.getPatients().subscribe(patients => this.patientList = patients);
    
    //Wczytanie listy lekarzy
    this.doctorSub = this.addVisitService.getDoctors().subscribe(doctors => this.doctorList = doctors);
    this.selectToday();
  }

  //Wyszukiwanie pacjenta za pomocą imienia nazwiska i numeru PESEL
  searchPatients(): void {
    this.patientSub = this.addVisitService.getPatients(this.patientSought).subscribe(patients => this.patientList = patients);
  }

  //Wyszukiwanie lekarza za pomocą imienia nazwiska i numeru licencji
  searchDoctors(): void {
    this.doctorSub = this.addVisitService.getDoctors(this.doctorSought).subscribe(doctors => this.doctorList = doctors);
  }

  //wybór pacjenta
  selectPatient(patient: Patient): void {
    this.selectedPatient=patient
    this.visitToAdd.patientId=patient.id;
    this.stage = 2;
  }

  //wybór lekarza
  selectDoctor(doctor: Doctor): void {
    this.selectedDoctor=doctor
    this.visitToAdd.doctorId=doctor.id;
    this.stage = 3;
  }

  selectDate(): void {
      this.fulldate=new Date(this.model.year,this.model.month-1,this.model.day,this.time.hour,this.time.minute);
      this.appointmentDateTime= this.fulldate.toISOString()
      this.visitToAdd.appointmentDateTime=this.appointmentDateTime
      this.stage = 4;
  }

  selectToday() {
    this.model = this.calendar.getToday();
  }

  // wysyła POST do API
  confirmAddVisit(){
    this.buttonConfirm=true;
    this.visitDetailsSub = this.addVisitService.postVisit(this.visitToAdd).subscribe(_visit => {
      this.visitDetails = _visit
      this.navigateToRecVisitList()
    })
  }

  //dodawanie nowego pacjenta i przypisanie go
  addNewPatient() {
    const addPatientModal = this.modalService.open(AddPatientComponent,{size: "lg"})
    addPatientModal.result.then((result) => {
      this.visitToAdd.patientId = result.id
      this.selectedPatient.firstName = result.firstName
      this.selectedPatient.lastName = result.lastName
      this.selectedPatient.peselNumber = result.peselNumber
      this.stage = 2;
      }, (reason) => { })

  }

  //powrót do listy wizyt recepcjonisty
  navigateToRecVisitList() {
    this.router.navigate(['/receptionist-visit-list/'])
  }
}