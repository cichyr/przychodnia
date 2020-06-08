import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http'
import {Observable} from 'rxjs'
import {tap} from 'rxjs/operators'
import {Injectable} from '@angular/core'
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertWindowComponent} from "../modals/alert-window/alert-window.component";
import {FieldError} from "../data/error/field-error";

@Injectable()
export class HttpBadRequestInterceptor implements HttpInterceptor {

  constructor(private modalService: NgbModal) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(tap(() => {
      },
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 400)
            return

          const modalRef = this.modalService.open(AlertWindowComponent)
          modalRef.componentInstance.errors = err.error() as Array<FieldError>

        }
      }))
  }
}
