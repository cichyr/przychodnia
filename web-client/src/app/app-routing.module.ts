import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PanelMainComponent} from './my-panel/panel-main/panel-main.component';
import {CommonModule} from '@angular/common';
import {MyPanelModule} from './my-panel/my-panel.module';
import { LabExamListComponent } from './lab-exam/lab-exam-list/lab-exam-list.component';
import {LabExamDetailsComponent} from './lab-exam/lab-exam-details/lab-exam-details.component'


const routes: Routes = [
  {path: 'main-panel', component: PanelMainComponent},
  {path: 'exam-list', component: LabExamListComponent},
  {path: 'exam-list/:id', component: LabExamDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
