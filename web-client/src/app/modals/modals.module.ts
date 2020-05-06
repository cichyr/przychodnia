import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TextInputComponent } from './text-input/text-input.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [TextInputComponent],
  imports: [
    CommonModule,
    NgbModule
  ],
  exports: [
    TextInputComponent,
  ]
})
export class ModalsModule { }
