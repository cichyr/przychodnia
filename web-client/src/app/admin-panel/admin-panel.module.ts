import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { ModalsModule } from '../modals/modals.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';



@NgModule({
  declarations: [UserListComponent],
  imports: [
    CommonModule,
    ModalsModule,
    NgbModule,
    FontAwesomeModule
  ]
})
export class AdminPanelModule { }
