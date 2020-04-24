import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'

import {AppRoutingModule} from './app-routing.module'
import {AppComponent} from './app.component'
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'
import {MyPanelModule} from './my-panel/my-panel.module'
import {LabExamModule} from './lab-exam/lab-exam.module'

import {NgbModule} from '@ng-bootstrap/ng-bootstrap'
import {BasicAuthHeaderInterceptor} from './interceptor/basic-auth-header-interceptor'

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    MyPanelModule,
    LabExamModule
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
