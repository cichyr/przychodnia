import { Component, OnInit } from '@angular/core';
import { LaboratoryExaminationService } from 'src/app/service/laboratory-examination.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LaboratoryExamination } from 'src/app/data/examination/laboratory-examination';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TextInputComponent } from 'src/app/modals/text-input/text-input.component';
import { UserService } from 'src/app/service/user.service';
import { Subscription } from 'rxjs';
import { User } from 'src/app/data/user/user';
import {DataExchangeService} from "../../service/data-exchange.service";

@Component({
  selector: 'app-lab-exam-details',
  templateUrl: './lab-exam-details.component.html',
  styleUrls: ['./lab-exam-details.component.css']
})
export class LabExamDetailsComponent implements OnInit {

  visitId: number
  examination: LaboratoryExamination
  userSub: Subscription
  labSub: Subscription
  visitSub: Subscription
  user: User
  exam_id: number
  alertResult: boolean
  alertNote: boolean

  constructor(private laboratoryService: LaboratoryExaminationService, private userService: UserService, private dataExchange: DataExchangeService, private router: Router, private route: ActivatedRoute, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.exam_id = Number(this.route.snapshot.paramMap.get('id'))

    this.visitSub = this.dataExchange.getCurrentVisit().subscribe(
      visitId => { this.visitId = visitId}
    )

    this.userSub = this.userService.getAuthenticationEvent().subscribe(user => {
      this.user = user
      if (this.user != null)
        this.labSub = this.laboratoryService.getLaboratoryExam(this.exam_id).subscribe(lab => this.examination = lab)
    })

    this.userService.getAuthenticationEvent().subscribe(user => {
      this.user = user
      if (this.user != null)
        this.labSub = this.laboratoryService.getLaboratoryExam(this.exam_id).subscribe(lab => this.examination = lab)
    })
  }

  // Change status
  changeStatus(status: string): void {
    if(status == 'Done' || status == 'CanWork') {
      if(this.examination.result.length != null && this.examination.result.length > 0) {
        this.labSub = this.laboratoryService.changeExaminationStatus(status, this.exam_id).subscribe(exam => this.examination = exam)
      } else {
        this.alertResult = true
      }
    }
    else if(status == 'Approve' || status == 'CanSup') {
      if(this.examination.supervisorNote.length != null && this.examination.supervisorNote.length > 0) {
        this.labSub = this.laboratoryService.changeExaminationStatus(status, this.exam_id).subscribe(exam => this.examination = exam)
      } else {
        this.alertNote = true
      }
    }
  }

  // Navigate to Examination list view
  navigateToList() {
    this.router.navigate(['exam-list'])
  }

  navigateToVisitDetails() {
    this.router.navigate(['/visit-details/' + this.visitId]);
  }

  // Open modal
  openPopup(type: string): void {
    // Opening modal
    const modalRef = this.modalService.open(TextInputComponent, { size: 'lg' })
    // Retrieve text from modal
    modalRef.result.then((result) => {
      this.setText(type, result)
    }, (reason) => { })

    // Set Modal title & preInput
    switch (type) {
      case 'result':
        modalRef.componentInstance.title = 'Edytuj wynik badania'
        modalRef.componentInstance.preInput = this.examination.result
        break

      case 'note':
        modalRef.componentInstance.title = 'Edytuj notatkę kierownika'
        modalRef.componentInstance.preInput = this.examination.supervisorNote
        break
    }
  }

  // Set text retrieved from Modal
  setText(type: string, input: string): void {
    this.labSub = this.laboratoryService.changeExaminationResultNote(type, input, this.exam_id).subscribe(exam => this.examination = exam)
  }

  // Date formating function
  public formatDate(date: string): string {
    switch (date) {
      case 'visit':
        var d = new Date(this.examination.creationDate)
        break;

      case 'execution':
        var d = new Date(this.examination.executionCancellationDate)
        break;

      case 'approval':
        var d = new Date(this.examination.approvalCancellationDate)
        break;
    }

    var month = '' + (d.getMonth() + 1)
    var day = '' + d.getDate()
    var year = d.getFullYear()

    if (month.length < 2)
      month = '0' + month;
    if (day.length < 2)
      day = '0' + day;

    return [year, month, day].join('-');
  }

  getRole(): string {
    return this.userService.getUserRole()
  }

  close(alert: string) {
    switch(alert) {
      case 'result':
        this.alertResult = false;
        break

      case 'note':
        this.alertNote = false;
        break
    }
  }


}


