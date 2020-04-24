import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LabExamListComponent } from './lab-exam-list/lab-exam-list.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { LabExamDetailsComponent } from './lab-exam-details/lab-exam-details.component'


@NgModule({
  declarations: [LabExamListComponent, LabExamDetailsComponent],
  imports: [
    CommonModule,
    NgbModule
  ],
  exports: [
    LabExamListComponent
  ]
})
export class LabExamModule { }
