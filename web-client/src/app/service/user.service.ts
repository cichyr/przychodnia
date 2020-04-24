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

  private readonly subject = new Subject<any>()

  private credentials: Credentials = null
  user: User = null

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

  getUserInfo(): Observable<User> {
    return this.subject.asObservable()
  }

  getBasicAuthToken(): string {
    return this.resolveBasicAuthToken()
  }

  private resolveBasicAuthToken(): string {

    if (this.credentials == null) {
      return ''
    }

    return 'Basic ' + btoa(`${this.credentials.username}:${this.credentials.password}`)
  }

}
