import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {PanelMainComponent} from './my-panel/panel-main/panel-main.component';
import {MyPanelModule} from './my-panel/my-panel.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MyPanelModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
