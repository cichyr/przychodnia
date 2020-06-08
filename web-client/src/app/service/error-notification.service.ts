import {Injectable} from '@angular/core';
import {FieldError} from "../data/error/field-error";
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ErrorNotificationService {

  private fieldErrorsSubject = new Subject<FieldError[]>()

  constructor() {
  }

  notifyFieldErrors(errors: FieldError[]) {
    this.fieldErrorsSubject.next(errors)
  }

  getFieldErrors(): Observable<FieldError[]> {
    return this.fieldErrorsSubject.asObservable()
  }
}
