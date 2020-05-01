import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http'

import {UserService} from '../service/user.service'
import {Router} from '@angular/router'
import {Observable} from 'rxjs'
import {tap} from 'rxjs/operators'
import {Injectable} from '@angular/core'

@Injectable()
export class HttpUnauthorizedInterceptor implements HttpInterceptor {

  constructor(public userService: UserService, private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(tap(() => {
      },
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 401) {
            return
          }
          this.router.navigate(['login-page'])
        }
      }))
  }
}
