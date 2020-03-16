import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PanelMainComponent} from './my-panel/panel-main/panel-main.component';
import {CommonModule} from '@angular/common';
import {MyPanelModule} from './my-panel/my-panel.module';


const routes: Routes = [
  {path: 'main-panel', component: PanelMainComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
