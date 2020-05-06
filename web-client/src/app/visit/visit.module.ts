import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReceptionistVisitListComponent } from './receptionist-visit-list/receptionist-visit-list.component';
import {BrowserModule} from "@angular/platform-browser";


@NgModule({
  declarations: [ReceptionistVisitListComponent],
  imports: [
    CommonModule,
    BrowserModule
  ]
})
export class VisitModule { }
