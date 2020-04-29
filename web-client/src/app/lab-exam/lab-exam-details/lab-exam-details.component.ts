import { Component, OnInit } from '@angular/core';
import { LaboratoryExaminationService } from 'src/app/service/laboratory-examination.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LaboratoryExamination } from 'src/app/data/examination/laboratory-examination';

@Component({
  selector: 'app-lab-exam-details',
  templateUrl: './lab-exam-details.component.html',
  styleUrls: ['./lab-exam-details.component.css']
})
export class LabExamDetailsComponent implements OnInit {

  examination: LaboratoryExamination
  exam_id: number

  constructor(private laboratoryService: LaboratoryExaminationService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.exam_id = Number(this.route.snapshot.paramMap.get('id'))
    this.examination = this.laboratoryService.getLaboratoryExam(this.exam_id)
  }

  // change status
  changeStatus(status: string): void {
    this.laboratoryService.changeExaminationStatus(status, this.examination)
  }

  // navigate to Examination list view
  navigateToList() {
    this.router.navigate(['exam-list'])
  }

  // open modal
  openPopup(type: string): void {
    
  }
}
