import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AccountDetailsComponent } from './account-details/account-details.component';



@NgModule({
  declarations: [UserListComponent, AccountDetailsComponent],
  imports: [
    CommonModule,
    NgbModule
  ]
})
export class AdminPanelModule { }
