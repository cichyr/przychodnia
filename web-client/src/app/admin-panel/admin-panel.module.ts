import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { ModalsModule } from '../modals/modals.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AccountDetailsComponent } from './account-details/account-details.component';
import {EditUserComponent} from "./edit-user/edit-user.component";



@NgModule({
  declarations: [UserListComponent, AccountDetailsComponent, EditUserComponent],
  imports: [
    CommonModule,
    ModalsModule,
    NgbModule,
    FontAwesomeModule
  ]
})
export class AdminPanelModule { }
