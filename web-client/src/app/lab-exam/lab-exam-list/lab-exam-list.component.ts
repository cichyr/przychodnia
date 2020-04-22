import { Component, OnInit } from '@angular/core';
import { LaboratoryExamination } from '../../data/examination/laboratory-examination'
// Mock imports
import { LaboratoryExaminationService } from '../../service/laboratory-examination.service'


@Component({
  selector: 'app-lab-exam-list',
  templateUrl: './lab-exam-list.component.html'
})
export class LabExamListComponent implements OnInit {

  __laboratoryList: LaboratoryExamination[]
  laboratoryList: LaboratoryExamination[]
  
  filters = {'ZLE': true, 'WYK': true, 'ANUL_LAB': true, 'ZATW': true, 'ANUL_KLAB': true}

  constructor(private laboratoryService: LaboratoryExaminationService) { }

  ngOnInit(): void {
    this.__laboratoryList = this.laboratoryService.getAllLaboratoryExams()
    this.laboratoryList = this.laboratoryService.getAllLaboratoryExams()
  }

  sort(option: number): void {
    switch(option){
      case 0:   // commision date newest first
        this.laboratoryList = this.__laboratoryList.sort((l1,l2) => {
          if(l1.visitId.finalizationCancellationDate < l2.visitId.finalizationCancellationDate) {
            return 1
          }

          if(l1.visitId.finalizationCancellationDate > l2.visitId.finalizationCancellationDate) {
            return -1
          }

          return 0
        })
        break

      case 1:   // commision date oldest first
        this.laboratoryList = this.__laboratoryList.sort((l1,l2) => {
          if(l1.visitId.finalizationCancellationDate > l2.visitId.finalizationCancellationDate) {
            return 1
          }
          if(l1.visitId.finalizationCancellationDate < l2.visitId.finalizationCancellationDate) {
            return -1
          }
          return 0
        })
        break

      case 2:   // finish date newest first
        this.laboratoryList = this.__laboratoryList.sort((l1,l2) => {
          if(l1.approvalCancellationDate < l2.approvalCancellationDate) {
            return 1
          }
          if(l1.approvalCancellationDate > l2.approvalCancellationDate) {
            return -1
          }
          return 0
        })
        break

      case 3:   // finish date oldest first
        this.laboratoryList = this.__laboratoryList.sort((l1,l2) => {
          if(l1.approvalCancellationDate > l2.approvalCancellationDate) {
            return 1
          }
          if(l1.approvalCancellationDate < l2.approvalCancellationDate) {
            return -1
          }
          return 0
        })
        break

      case 4:   // id lowest first
        this.laboratoryList = this.__laboratoryList.sort((l1,l2) => l1.id - l2.id)
        break

      case 5:   // id highest first
        this.laboratoryList = this.__laboratoryList.sort((l1,l2) => l2.id - l1.id)
        break
    }
  }

  // Formating function
  formatDate(date: Date) : string {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
  }

}
