import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PanelMainComponent} from './my-panel/panel-main/panel-main.component';
import {CommonModule} from '@angular/common';
import {MyPanelModule} from './my-panel/my-panel.module';
import {DoctorVisitListComponent} from "./visit/doctor-visit-list/doctor-visit-list.component";


const routes: Routes = [
  {path: 'main-panel', component: PanelMainComponent},
  {path: 'doctor-visit-list', component: DoctorVisitListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
