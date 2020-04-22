import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LabExamListComponent } from './lab-exam-list/lab-exam-list.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap'


@NgModule({
  declarations: [LabExamListComponent],
  imports: [
    CommonModule,
    NgbModule
  ],
  exports: [
    LabExamListComponent
  ]
})
export class LabExamModule { }
