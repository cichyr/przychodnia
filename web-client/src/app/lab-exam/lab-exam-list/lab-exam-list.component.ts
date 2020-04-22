import { Component, OnInit } from '@angular/core';
import { LaboratoryExamination } from '../../data/examination/laboratory-examination'
// Mock imports
import { LaboratoryExaminationService } from '../../service/laboratory-examination.service'


@Component({
  selector: 'app-lab-exam-list',
  templateUrl: './lab-exam-list.component.html'
})
export class LabExamListComponent implements OnInit {

  laboratoryList: LaboratoryExamination[]
  
  

  constructor(private laboratoryService: LaboratoryExaminationService) { }

  ngOnInit(): void {
    this.laboratoryList = this.laboratoryService.getAllLaboratoryExams()
  }

}
