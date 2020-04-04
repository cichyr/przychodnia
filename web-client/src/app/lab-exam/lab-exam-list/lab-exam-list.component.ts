import { Component, OnInit } from '@angular/core';
import { LaboratoryExamination } from '../../data/examination/laboratory-examination'
// Mock imports
import { LaboratoryExaminationService } from '../../service/laboratory-examination.service'


@Component({
  selector: 'app-lab-exam-list',
  templateUrl: './lab-exam-list.component.html',
  styleUrls: ['./lab-exam-list.component.css']
})
export class LabExamListComponent implements OnInit {

  laboratoryList: LaboratoryExamination[]
  
  someArray = ['qwe', 'abc', 'xyz']

  

  constructor(private laboratoryService: LaboratoryExaminationService) { }

  ngOnInit(): void {
    this.laboratoryList = this.laboratoryService.getAllLaboratoryExams()
  }

}
