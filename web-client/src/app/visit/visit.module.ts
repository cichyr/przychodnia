import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReceptionistVisitListComponent } from './receptionist-visit-list/receptionist-visit-list.component';
import {BrowserModule} from "@angular/platform-browser";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [ReceptionistVisitListComponent],
  imports: [
    CommonModule,
    BrowserModule,
    NgbModule
  ]
})
export class VisitModule { }
