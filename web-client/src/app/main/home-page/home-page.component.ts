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

  private sub: Subscription = null
  private user: User = null
  private userFullName: string = null

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.sub = this.userService.getAuthenticationEvent().subscribe(
      next => {
        this.user = next
        if (this.user != null) {
          this.userFullName = this.user.firstName + ' ' + this.user.lastName
        }
      })
  }

  usernamePresent() {
    return this.user != null
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe()
  }

}
