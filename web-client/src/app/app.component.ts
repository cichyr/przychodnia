import {Component, OnDestroy, OnInit} from '@angular/core'
import {Router} from '@angular/router'
import {Subscription} from 'rxjs'
import {UserService} from './service/user.service'
import {environment} from '../environments/environment.dev'
import {Credentials} from './data/user/credentials'
import {map, tap} from 'rxjs/operators'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'web-client'
  authSubscription: Subscription
  userFullName: String

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

  signOut() {
    this.userService.signOut()
    this.navigateToLoginPage()
  }

  ngOnDestroy(): void {
    if (this.authSubscription != null) this.authSubscription.unsubscribe()
  }

}
