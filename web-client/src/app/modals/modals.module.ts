import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TextInputComponent } from './text-input/text-input.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PasswordInputComponent } from './password-input/password-input.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AlertWindowComponent } from './alert-window/alert-window.component';



@NgModule({
  declarations: [TextInputComponent, PasswordInputComponent, AlertWindowComponent],
  imports: [
    CommonModule,
    NgbModule,
    ReactiveFormsModule
  ],
  exports: [
    TextInputComponent,
    PasswordInputComponent,
    AlertWindowComponent
  ]
})
export class ModalsModule { }
