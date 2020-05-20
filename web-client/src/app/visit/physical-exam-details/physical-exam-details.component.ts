import { Component, OnInit } from '@angular/core';
import { DataExchangeService } from 'src/app/service/data-exchange.service';
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { PhysicalExamination } from 'src/app/data/examination/physical-examination';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/data/user/user';
import { PhysicalExaminationDetailsService } from 'src/app/service/physical-examination-details.service';

@Component({
  selector: 'app-physical-exam-details',
  templateUrl: './physical-exam-details.component.html',
  styleUrls: ['./physical-exam-details.component.css']
})
export class PhysicalExamDetailsComponent implements OnInit {

  visitId: number
  physicalExaminationId: number
  subscription: Subscription

  physicalExamination: PhysicalExamination
  userSub: Subscription
  user: User
  physicalExamSub: Subscription

  constructor(private router: Router, private dataExchange: DataExchangeService, private userService: UserService, private physicalExaminationDetailsService: PhysicalExaminationDetailsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.physicalExaminationId = Number(this.route.snapshot.paramMap.get('id'));
    
    this.subscription = this.dataExchange.getCurrentVisit().subscribe(
      visitId => { this.visitId = visitId}
    )

    this.userSub =
      this.userService.getAuthenticationEvent().subscribe(user => {
          this.user = user;
          if (this.user != null)
            this.physicalExamSub = this.physicalExaminationDetailsService.getPhysicalExamination(this.physicalExaminationId).subscribe(_physicalExamination => this.physicalExamination = _physicalExamination);
        }
      );
  }

  //Powrót do szczegółów wizyty
  navigateToVisitDetails() {
    this.router.navigate(['/visit-details/' + this.visitId]);
  }

}
