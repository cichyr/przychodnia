import { Injectable } from '@angular/core';
import { ReplaySubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataExchangeService {

  private subject=new ReplaySubject<any>()

  constructor() { }

  getCurrentVisit() :Observable<number>{
    return this.subject.asObservable()
  }

  setCurrentVisit(visitId :number){
    this.subject.next(visitId)
  }

}
