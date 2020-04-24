import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http'
import {Observable} from 'rxjs'
import {Injectable} from '@angular/core'
import {UserService} from '../service/user.service'

@Injectable()
export class BasicAuthHeaderInterceptor implements HttpInterceptor {

  constructor(private userService: UserService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let copiedReq
    if (this.userService.user != null) {
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
