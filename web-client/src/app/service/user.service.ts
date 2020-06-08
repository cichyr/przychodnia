import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {User} from '../data/user/user'
import {BehaviorSubject, Observable} from 'rxjs'
import {Credentials} from '../data/user/credentials'
import {tap} from 'rxjs/operators'
import {UserBasic} from '../data/user/user-basic'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private user: User = null
  private readonly subject = new BehaviorSubject<any>(null)
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

  getUserRole(): string {
    if (this.hasUser()) {
      return this.user.role
    } else {
      return "NONE"
    }
  }

  getId(): number {
    if (this.hasUser()) {
      return this.user.id
    } else {
      return 0
    }
  }

  getUserList(user?: User): Observable<UserBasic[]> {
    if (user !== undefined) {
      let useAnd = false
      let query = ''
      if (user.id != null) {
        query += 'id=' + user.id
        useAnd = true
      }
      if (user.username) {
        if (useAnd) {
          query += '&'
        }
        query += 'username=' + user.username
        useAnd = true
      }
      if (user.firstName) {
        if (useAnd) {
          query += '&'
        }
        query += 'first_name=' + user.firstName
        useAnd = true
      }
      if (user.lastName) {
        if (useAnd) {
          query += '&'
        }
        query += 'last_name=' + user.lastName
        useAnd = true
      }
      console.log(query)
      return this.http.get<UserBasic[]>(`http://localhost:8080/api/users?${query}`)
    } else {
      return this.http.get<UserBasic[]>(`http://localhost:8080/api/users`)
    }
  }

  getUser(id: number, role_id: number): Observable<User> {
    return this.http.get<User>(`http://localhost:8080/api/user_details?employee_id=${id}&role_id=${role_id}`)
  }

  updatePassword(id: number, role_id: number, new_hash: string): Observable<User> {
    return this.http.put<User>(`http://localhost:8080/api/user_details/password?employee_id=${id}&role_id=${role_id}`, {
      "password": new_hash
    })
  }

  postNewUser(user: User): Observable<User> {

    console.log(user)

    return this.http.post<User>(`http://localhost:8080/api/users`,{
      "licenseCode": user.licenseCode,
      "role": user.role,
      "username": user.username,
      "password": user.password,
      "status": "ENABLED",
      "firstName": user.firstName,
      "lastName": user.lastName,
      "city": user.city,
      "streetAddress1": user.streetAddress1,
      "streetAddress2": null,
      "zipCode": user.zipCode,
      "region": user.region,
      "contactNumber": user.contactNumber
    })
  }

  editUser(user: User, role: number): Observable<User> {
    console.log(user)
    return this.http.put<User>(`http://localhost:8080/api/user_details?employee_id=${user.id}&role_id=${role}`, user)

  }

  private resolveBasicAuthToken(): string {

    if (this.credentials == null) return ''

    return 'Basic ' + btoa(`${this.credentials.username}:${this.credentials.password}`)
  }

}
