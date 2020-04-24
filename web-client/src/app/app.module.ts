import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'
import {NgbModule} from '@ng-bootstrap/ng-bootstrap'

import {AppRoutingModule} from './app-routing.module'
import {AppComponent} from './app.component'
import {LabExamModule} from './lab-exam/lab-exam.module'
import {BasicAuthHeaderInterceptor} from './interceptor/basic-auth-header-interceptor'
import {MainModule} from './main/main.module'
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    LabExamModule,
    MainModule
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
