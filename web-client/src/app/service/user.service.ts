import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {User} from '../data/user/user'
import {Observable, ReplaySubject} from 'rxjs'
import {Credentials} from '../data/user/credentials'
import {tap} from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: User = null
  private readonly subject = new ReplaySubject<any>()
  private credentials: Credentials = null

  constructor(private http: HttpClient) {
  }

  signIn(credentials: Credentials): Observable<User> {

    this.credentials = credentials
    const token = this.resolveBasicAuthToken()

    return this.http.get<User>('http://localhost:8080/api/userinfo', {headers: {'Authorization': token}})
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

  getAuthenticationEvent(): Observable<User> {
    return this.subject.asObservable()
  }

  getBasicAuthToken(): string {
    return this.resolveBasicAuthToken()
  }

  hasUser() {
    return this.user != null
  }

  private resolveBasicAuthToken(): string {

    if (this.credentials == null) return ''

    return 'Basic ' + btoa(`${this.credentials.username}:${this.credentials.password}`)
  }

}
