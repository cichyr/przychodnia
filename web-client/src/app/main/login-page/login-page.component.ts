import {Router} from '@angular/router'
import {FormControl, FormGroup} from '@angular/forms'
import {Component, OnDestroy, OnInit} from '@angular/core'
import {Subscription} from 'rxjs'

import {UserService} from '../../service/user.service'
import {Credentials} from '../../data/user/credentials'

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit, OnDestroy {

  recentLoginFailed = false
  credentialsForm = new FormGroup({
    username: new FormControl(),
    password: new FormControl()
  })
  private signInSub: Subscription

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
  }

  signIn() {
    const credentials = new Credentials(this.credentialsForm.value.username, this.credentialsForm.value.password)

    this.signInSub = this.userService.signIn(credentials).subscribe(
      _ => {
        this.recentLoginFailed = false
        this.router.navigate(['home'])
      },
      _ => {
        this.recentLoginFailed = true
      }
    )
  }

  ngOnDestroy(): void {
    if (this.signInSub != null) this.signInSub.unsubscribe()
  }

}
