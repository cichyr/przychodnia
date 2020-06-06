import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { AddUserComponent } from './add-user/add-user.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [UserListComponent, AddUserComponent],
  imports: [
    CommonModule,
    NgbModule
  ]
})
export class AdminPanelModule { }
