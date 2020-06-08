import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'
import {NgbModule} from '@ng-bootstrap/ng-bootstrap'

import {AppRoutingModule} from './app-routing.module'
import {AppComponent} from './app.component'
import {LabExamModule} from './lab-exam/lab-exam.module'
import {BasicAuthHeaderInterceptor} from './interceptor/basic-auth-header-interceptor'
import {MainModule} from './main/main.module'
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'
import {CommonModule} from "@angular/common";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'
import {VisitModule} from './visit/visit.module'
import { AdminPanelModule } from './admin-panel/admin-panel.module'
import {HttpBadRequestInterceptor} from "./interceptor/http-bad-request-interceptor";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AdminPanelModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    VisitModule,
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
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpBadRequestInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
