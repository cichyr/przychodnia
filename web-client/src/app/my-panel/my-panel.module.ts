import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PanelMainComponent} from './panel-main/panel-main.component';


@NgModule({
  declarations: [PanelMainComponent],
  imports: [CommonModule],
  exports: [PanelMainComponent]
})
export class MyPanelModule {
}
