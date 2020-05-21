import { Component, OnInit, OnDestroy } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {VisitDetailsService} from "../../service/visit-details.service";
import {User} from "../../data/user/user";
import {Subscription} from "rxjs";
import {UserService} from "../../service/user.service";
import {VisitDetails} from "../../data/visit/visit-details";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {TextInputComponent} from "../../modals/text-input/text-input.component";
import { DataExchangeService } from 'src/app/service/data-exchange.service';

@Component({
  selector: 'app-visit-details',
  templateUrl: './visit-details.component.html',
  styleUrls: ['./visit-details.component.css']
})
export class VisitDetailsComponent implements OnInit {

  visit: VisitDetails
  visitId: number
  user: User
  userSub: Subscription
  visitSub: Subscription

  constructor(private router: Router, private dataExchange: DataExchangeService, private visitService: VisitDetailsService, private userService: UserService, private route: ActivatedRoute, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.visitId = Number(this.route.snapshot.paramMap.get('id'));
    this.dataExchange.setCurrentVisit(this.visitId)
    this.userSub =
      this.userService.getAuthenticationEvent().subscribe(user => {
          this.user = user;
          if (this.user != null)
            this.visitSub = this.visitService.getVisit(this.visitId).subscribe(_visit => this.visit = _visit);
        }
      );
  }

  finalizeVisit(): void{
    this.visitSub = this.visitService.finalizeVisit(this.visit.visitId).subscribe(visit => this.visit = visit);
  }

  cancelVisit(): void{
    this.visitSub = this.visitService.cancelVisit(this.visit.visitId).subscribe(visit => this.visit = visit);
  }

  navigateToDoctorVisitList(): void {
    this.router.navigate(['doctor-visit-list']);
  }

  navigateToAddPhysicalExam() {
    this.router.navigate(['/add-physical-exam'])
  }

  navigateToPhysicalExamDetails(physicalExamId: number) {
    this.dataExchange.setCurrentVisit(this.visitId)
    this.router.navigate(['/physical-exam-details/'+ physicalExamId])
  }

  navigateToAddLabExam() {
    this.router.navigate(['/add-lab-exam'])
  }

  navigateToLaboratoryExamDetails(laboratoryExamId: number) {
    this.dataExchange.setCurrentVisit(this.visitId)
    this.router.navigate(['/laboratory-exam-details/'+ laboratoryExamId])
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
      case 'description':
        modalRef.componentInstance.title = 'Edytuj opis'
        modalRef.componentInstance.preInput = ""
        break

      case 'diagnose':
        modalRef.componentInstance.title = 'Edytuj diagnozę'
        modalRef.componentInstance.preInput = ""
        break
    }
  }

  // Set text retrieved from Modal
  setText(type: String, result: String): void {

    switch (type) {
      case 'description':
        this.visit.description = result;
        break

      case 'diagnose':
        this.visit.diagnose = result;
        break
    }

    this.visitSub = this.visitService.setInterview(this.visit.visitId, this.visit.description, this.visit.diagnose).subscribe(visit => this.visit = visit);
  }

  ngOnDestroy(): void
  {
    if (this.userSub != null)
      this.userSub.unsubscribe()

    if(this.visitSub != null)
      this.visitSub.unsubscribe()
  }
}
