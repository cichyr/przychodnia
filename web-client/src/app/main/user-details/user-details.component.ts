import {Component, OnDestroy, OnInit} from '@angular/core'
import {Subscription} from 'rxjs'
import {UserService} from '../../service/user.service'
import {User} from '../../data/user/user'
import {Role} from '../../data/user/role.enum'
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit, OnDestroy {

  authSubscription: Subscription
  user: User

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.authSubscription = this.userService.getAuthenticationEvent().subscribe(
      user => this.user = user
    )
  }

  translateRole(): String {
    switch (this.user.role) {
      case Role.DOCTOR: return 'Lekarz'
      case Role.RECEPTIONIST: return 'Recepcjonista'
      case Role.LAB_WORKER: return 'Laborant'
      case Role.LAB_SUPERVISOR: return 'Kierownik Laboratorium'
      case Role.ADMINISTRATOR: return 'Administrator'
    }
  }

  ngOnDestroy(): void {
    if (this.authSubscription != null) this.authSubscription.unsubscribe()
  }

}
