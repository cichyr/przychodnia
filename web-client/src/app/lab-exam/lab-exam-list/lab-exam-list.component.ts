import { Component, OnInit } from '@angular/core';
import { LaboratoryExamination } from '../../data/examination/laboratory-examination'
// Mock imports
import { laboratories, pat } from '../../data/LabExamMock'


@Component({
  selector: 'app-lab-exam-list',
  templateUrl: './lab-exam-list.component.html',
  styleUrls: ['./lab-exam-list.component.css']
})
export class LabExamListComponent implements OnInit {

  laboratoryList: LaboratoryExamination[] = laboratories

  someArray = ['qwe', 'abc', 'xyz']

  patient = pat

  constructor() { }

  ngOnInit(): void {
  }

}
