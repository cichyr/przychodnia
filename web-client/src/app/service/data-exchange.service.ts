import { Injectable } from '@angular/core';
import { ReplaySubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataExchangeService {

  private subject=new ReplaySubject<any>()
  private subject2=new ReplaySubject<any>()

  constructor() { }

  getCurrentVisit() :Observable<number>{
    return this.subject.asObservable()
  }

  setCurrentVisit(visitId :number){
    this.subject.next(visitId)
  }

  getCurrentUserId() :Observable<number>{
    return this.subject.asObservable()
  }

  getCurrentUserRoleId() :Observable<number>{
    return this.subject2.asObservable()
  }

  setCurrentUser(userId :number, roleId: number){
    this.subject.next(userId)
    this.subject2.next(userId)
  }


}
