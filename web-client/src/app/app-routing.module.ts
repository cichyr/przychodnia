import {NgModule} from '@angular/core'
import {RouterModule, Routes} from '@angular/router'

import {LabExamListComponent} from './lab-exam/lab-exam-list/lab-exam-list.component'
import {LabExamDetailsComponent} from './lab-exam/lab-exam-details/lab-exam-details.component'
import {HomePageComponent} from './main/home-page/home-page.component'
import {LoginPageComponent} from './main/login-page/login-page.component'
import {UserDetailsComponent} from './main/user-details/user-details.component'
import {AuthGuardService} from './service/auth-guard.service'
import {ReceptionistVisitListComponent} from "./visit/receptionist-visit-list/receptionist-visit-list.component";

const routes: Routes = [
  {path: 'login-page', component: LoginPageComponent},
  {path: 'receptionist-visit-list', component: ReceptionistVisitListComponent},
  {
    path: '', canActivate: [AuthGuardService], children: [
      {path: 'home', component: HomePageComponent},
      {path: 'user-details', component: UserDetailsComponent},
      {path: 'exam-list/:id', component: LabExamDetailsComponent}
    ]
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
