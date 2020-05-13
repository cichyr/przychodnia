import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CommonModule} from '@angular/common';
import {DoctorVisitListComponent} from "./visit/doctor-visit-list/doctor-visit-list.component";

import {LabExamListComponent} from './lab-exam/lab-exam-list/lab-exam-list.component'
import {LabExamDetailsComponent} from './lab-exam/lab-exam-details/lab-exam-details.component'
import {HomePageComponent} from './main/home-page/home-page.component'
import {LoginPageComponent} from './main/login-page/login-page.component'
import {UserDetailsComponent} from './main/user-details/user-details.component'
import {AuthGuardService} from './service/auth-guard.service'
import {ReceptionistVisitListComponent} from "./visit/receptionist-visit-list/receptionist-visit-list.component";
import {VisitDetailsComponent} from "./visit/visit-details/visit-details.component";

const routes: Routes = [
  {path: 'login-page', component: LoginPageComponent},
  {
    path: '', canActivate: [AuthGuardService], children: [
      {path: 'home', component: HomePageComponent},
      {path: 'user-details', component: UserDetailsComponent},
      {path: 'receptionist-visit-list', component: ReceptionistVisitListComponent},
      {path: 'exam-list/:id', component: LabExamDetailsComponent},
      {path: 'exam-list', component: LabExamListComponent},
      {path: 'doctor-visit-list', component: DoctorVisitListComponent},
      {path: 'visit-details/:id', component: VisitDetailsComponent}
    ]
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
