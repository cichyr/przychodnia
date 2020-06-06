import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [UserListComponent, EditUserComponent],
  imports: [
    CommonModule,
    NgbModule
  ]
})
export class AdminPanelModule { }
