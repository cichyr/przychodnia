import {Component, OnDestroy, OnInit} from '@angular/core'
import {Router} from '@angular/router'
import {Subscription} from 'rxjs'
import {UserService} from './service/user.service'
import {environment} from '../environments/environment.dev'
import {Credentials} from './data/user/credentials'
import {map, tap} from 'rxjs/operators'
import {faMicroscope, faNotesMedical, faThLarge, faUser} from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'web-client'
  authSubscription: Subscription
  userFullName: String

  // icon variables
  microscope = faMicroscope
  visit = faNotesMedical
  menu = faThLarge
  login = faUser

  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
    this.authSubscription = this.userService.getAuthenticationEvent().subscribe(
      user => {
        console.log(user)
        if (user != null) this.userFullName = user.firstName + ' ' + user.lastName
        else this.userFullName = null
      })
  }

  navigateToHomePage() {
    this.router.navigate(['home'])
  }

  navigateToLoginPage() {
    this.router.navigate(['login-page'])
  }

  navigateToUserDetails() {
    this.router.navigate(['user-details'])
  }

  navigateToLab() {
    if(this.getRole() == 'LABW' || this.getRole() == 'LABS') {
      this.router.navigate(['/exam-list/'])
    }
  }

  navigateToRecVisitList() {
    if(this.getRole() == 'REC'){
      this.router.navigate(['/receptionist-visit-list/'])
    }
  }

  navigateToVisit() {
    if(this.getRole() == 'REC') {
      this.router.navigate(['/receptionist-visit-list/'])
    }
    else if(this.getRole() == 'DOC') {
      this.router.navigate(['/doctor-visit-list/'])
    }
  }

  signOut() {
    this.userService.signOut()
    this.navigateToLoginPage()
  }

  ngOnDestroy(): void {
    if (this.authSubscription != null) this.authSubscription.unsubscribe()
  }

  getRole(): string {
    return this.userService.getUserRole()
  }

}
