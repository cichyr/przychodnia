import {UserService} from '../service/user.service'
import {Injectable} from '@angular/core'
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http'
import {Observable} from 'rxjs'

@Injectable()
export class BasicAuthHeaderInterceptor implements HttpInterceptor {

  constructor(private userService: UserService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let copiedReq
    if (this.userService.hasUser()) {
      copiedReq = request.clone({
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': this.userService.getBasicAuthToken()
        })
      })
    } else {
      copiedReq = request.clone()
    }

    return next.handle(copiedReq)
  }

}
