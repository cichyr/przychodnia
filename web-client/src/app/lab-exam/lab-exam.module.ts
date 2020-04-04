import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LabExamListComponent } from './lab-exam-list/lab-exam-list.component';



@NgModule({
  declarations: [LabExamListComponent],
  imports: [
    CommonModule
  ],
  exports: [
    LabExamListComponent
  ]
})
export class LabExamModule { }
