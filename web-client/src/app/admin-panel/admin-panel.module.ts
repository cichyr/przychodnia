import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { EditUserComponent } from './edit-user/edit-user.component';



@NgModule({
  declarations: [UserListComponent, EditUserComponent],
  imports: [
    CommonModule
  ]
})
export class AdminPanelModule { }
