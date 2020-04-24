import {Component, OnDestroy, OnInit} from '@angular/core'
import {Subscription} from 'rxjs'
import {UserService} from '../../service/user.service'
import {User} from '../../data/user/user'

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit, OnDestroy {

  private userSub: Subscription
  private user: User
  private userFullName = ''

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userSub = this.userService.getAuthenticationEvent().subscribe(
      user => {
        this.user = user
        if (this.user != null)
          this.userFullName = this.user.firstName + ' ' + this.user.lastName
        else
          this.userFullName = ''

      })
  }

  usernamePresent() {
    return this.user != null
  }

  ngOnDestroy(): void {
    if (this.userSub != null) this.userSub.unsubscribe()
  }

}
