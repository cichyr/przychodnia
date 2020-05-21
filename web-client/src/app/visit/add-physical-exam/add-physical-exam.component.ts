import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DataExchangeService } from 'src/app/service/data-exchange.service';
import { Subscription } from 'rxjs';
import { User } from 'src/app/data/user/user';
import { ExaminationDictionary } from 'src/app/data/examination/examination-dictionary';
import { UserService } from 'src/app/service/user.service';
import { AddVisitService } from 'src/app/service/add-visit.service';
import { ShortPhysicalExamination } from 'src/app/data/examination/short-physical-examination';
import { VisitDetails } from 'src/app/data/visit/visit-details';

@Component({
  selector: 'app-add-physical-exam',
  templateUrl: './add-physical-exam.component.html',
  styleUrls: ['./add-physical-exam.component.css']
})
export class AddPhysicalExamComponent implements OnInit, OnDestroy {
  user: User
  userSub: Subscription

  visitId: number;
  subscription: Subscription

  examinationDictionaryList: ExaminationDictionary[]
  examinationDictionary: ExaminationDictionary
  examinationDictionarySought: ExaminationDictionary = new ExaminationDictionary()
  examinationDictionarySub: Subscription

  shortPhysicalExamination: ShortPhysicalExamination = new ShortPhysicalExamination()
  visitDetails: VisitDetails
  visitDetailsSub: Subscription

  constructor(private router: Router, private userService: UserService, private addVisitService: AddVisitService, private dataExchange: DataExchangeService, private route: ActivatedRoute) { }
  stage = 1;

  ngOnInit(): void {
    this.subscription = this.dataExchange.getCurrentVisit().subscribe(
      visitId => { this.visitId = visitId}
    )

    this.userSub =
      this.userService.getAuthenticationEvent().subscribe(user => {
          this.user = user;
          if (this.user != null)
            this.examinationDictionarySub = this.addVisitService.getPossiblePhysicalExamination().subscribe(examinationDictionarys => this.examinationDictionaryList = examinationDictionarys);
        }
      );
  }
  
  //Ustawia typ badania fizykalnego
  selectExaminationDictionary(examinationDictionary: ExaminationDictionary): void{
    this.shortPhysicalExamination.code=examinationDictionary.id
    this.stage = 2
  }

  //Wysyła POST do API
  confirmAddPhysicalExamination(){
    if(this.shortPhysicalExamination.result!==""){
      this.userSub =
        this.userService.getAuthenticationEvent().subscribe(user => {
            this.user = user;
            if (this.user != null)
              this.visitDetailsSub = this.addVisitService.postPhysicalExamination(this.visitId, this.shortPhysicalExamination).subscribe(_visit => this.visitDetails = _visit)
          }
        );
      this.navigateToVisitDetails()
    }
  }

  //Powrót do szczegółów wizyty
  navigateToVisitDetails() {
    this.router.navigate(['/visit-details/' + this.visitId]);
  }
  
  ngOnDestroy(){
    this.subscription.unsubscribe()
  }
}
