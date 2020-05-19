import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DataExchangeService } from 'src/app/service/data-exchange.service';
import { Subscription } from 'rxjs';
import { User } from 'src/app/data/user/user';
import { ExaminationDictionary } from 'src/app/data/examination/examination-dictionary';
import { UserService } from 'src/app/service/user.service';
import { AddVisitService } from 'src/app/service/add-visit.service';

@Component({
  selector: 'app-add-lab-exam',
  templateUrl: './add-lab-exam.component.html',
  styleUrls: ['./add-lab-exam.component.css']
})
export class AddLabExamComponent implements OnInit, OnDestroy {
  user: User
  userSub: Subscription

  visitId: number;
  subscription: Subscription

  examinationDictionaryList: ExaminationDictionary[]
  examinationDictionary: ExaminationDictionary
  examinationDictionarySought: ExaminationDictionary = new ExaminationDictionary()
  examinationDictionarySub: Subscription

  result = ''

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
            this.examinationDictionarySub = this.addVisitService.getPossibleLabExamination().subscribe(examinationDictionarys => this.examinationDictionaryList = examinationDictionarys);
        }
      );
  }
  
  //TODO wyszukiwanie typu badania laboratoryjnego?
  searchExaminationDictionary(): void {
  }

  //TODO wysłać post do api
  selectExaminationDictionary(examinationDictionary: ExaminationDictionary): void{
    this.examinationDictionary=examinationDictionary
    this.stage = 2
  }

  //Powrót do szczegółów wizyty
  navigateToVisitDetails() {
    this.router.navigate(['/visit-details/' + this.visitId]);
  }
  
  ngOnDestroy(){
    this.subscription.unsubscribe()
  }
}
