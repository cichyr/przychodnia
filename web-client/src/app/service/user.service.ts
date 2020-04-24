import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {User} from '../data/user/user'
import {Observable, Subject} from 'rxjs'
import {Credentials} from '../data/user/credentials'
import {tap} from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private subject = new Subject<any>()
  user: User = null

  constructor(private http: HttpClient) {
  }

  signIn(credentials: Credentials): Observable<User> {

    const basicAuthToken = 'Basic ' + btoa(`${credentials.username}:${credentials.password}`)

    return this.http.get<User>('http://localhost:8080/api/userinfo', {headers: {'Authorization': basicAuthToken}})
      .pipe(tap(user => this.user = user))
      .pipe(tap(user => this.subject.next(user)))

  }

  signOut() {

    if (!this.user) {
      return
    }

    this.user = null
    this.subject.next(null)
  }

  getUserInfo(): Observable<User> {
    return this.subject.asObservable()
  }

}
