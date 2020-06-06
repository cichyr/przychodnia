import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { ModalsModule } from '../modals/modals.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [UserListComponent],
  imports: [
    CommonModule,
    ModalsModule,
    NgbModule
  ]
})
export class AdminPanelModule { }
