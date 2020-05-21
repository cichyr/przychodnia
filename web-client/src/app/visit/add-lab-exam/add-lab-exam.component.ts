import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DataExchangeService } from 'src/app/service/data-exchange.service';
import { Subscription } from 'rxjs';
import { User } from 'src/app/data/user/user';
import { ExaminationDictionary } from 'src/app/data/examination/examination-dictionary';
import { UserService } from 'src/app/service/user.service';
import { AddVisitService } from 'src/app/service/add-visit.service';
import { ShortLaboratoryExamination } from 'src/app/data/examination/short-laboratory-examination';
import { VisitDetails } from 'src/app/data/visit/visit-details';

@Component({
  selector: 'app-add-lab-exam',
  templateUrl: './add-lab-exam.component.html',
  styleUrls: ['./add-lab-exam.component.css']
})
export class AddLabExamComponent implements OnInit, OnDestroy {
  visitId: number;
  subscription: Subscription

  examinationDictionaryList: ExaminationDictionary[]
  examinationDictionary: ExaminationDictionary
  examinationDictionarySought: ExaminationDictionary = new ExaminationDictionary()
  examinationDictionarySub: Subscription

  shortLaboratoryExamination: ShortLaboratoryExamination = new ShortLaboratoryExamination()
  visitDetails: VisitDetails
  visitDetailsSub: Subscription

  buttonConfirm=false

  constructor(private router: Router, private addVisitService: AddVisitService, private dataExchange: DataExchangeService) { }
  stage = 1;

  ngOnInit(): void {
    this.subscription = this.dataExchange.getCurrentVisit().subscribe(
      visitId => { this.visitId = visitId}
    )

    this.examinationDictionarySub = this.addVisitService.getPossibleLabExamination().subscribe(examinationDictionarys => this.examinationDictionaryList = examinationDictionarys);
  }

  // Wybiera typ badania laboratoryjnego
  selectExaminationDictionary(examinationDictionary: ExaminationDictionary): void{
    this.shortLaboratoryExamination.code=examinationDictionary.id
    this.stage = 2
  }

  // Wysyła POST do API
  confirmAddLaboratoryExamination(){
    if(this.shortLaboratoryExamination.doctorNotes!=""){
      this.visitDetailsSub = this.addVisitService.postLaboratoryExamination(this.visitId, this.shortLaboratoryExamination).subscribe(_visit =>{
        this.visitDetails = _visit
        this.navigateToVisitDetails() 
      })
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
