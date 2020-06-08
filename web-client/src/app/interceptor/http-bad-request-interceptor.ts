import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http'
import {Observable} from 'rxjs'
import {tap} from 'rxjs/operators'
import {Injectable} from '@angular/core'
import {FieldError} from "../data/error/field-error";
import {ErrorNotificationService} from "../service/error-notification.service";

@Injectable()
export class HttpBadRequestInterceptor implements HttpInterceptor {

  constructor(private errorNotificationService: ErrorNotificationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(tap(() => {
      },
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 400)
            return
          this.errorNotificationService.notifyFieldErrors(err.error.errors.map(error => new FieldError(error.field, error.message)))
        }
      }))
  }
}
