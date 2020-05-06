import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'
import {NgbModule} from '@ng-bootstrap/ng-bootstrap'

import {AppRoutingModule} from './app-routing.module'
import {AppComponent} from './app.component'
import {LabExamModule} from './lab-exam/lab-exam.module'
import {BasicAuthHeaderInterceptor} from './interceptor/basic-auth-header-interceptor'
import {MainModule} from './main/main.module'
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {PanelMainComponent} from './my-panel/panel-main/panel-main.component';
import {MyPanelModule} from './my-panel/my-panel.module';
import {VisitModule} from './visit/visit.module'

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    VisitModule
    NgbModule,
    LabExamModule,
    MainModule,
    FontAwesomeModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthHeaderInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
