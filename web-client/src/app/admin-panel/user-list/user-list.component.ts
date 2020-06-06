import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/data/user/user';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';
import { Role } from 'src/app/data/user/role.enum';
import { faSearch } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  filters = {'active': true, 'inactive': true}
  userList: User[]
  user: User
  userSub: Subscription
  search = faSearch
  seekedUser: User = new User()

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    // Subscription
    this.userSub = this.userService.getAuthenticationEvent().subscribe(user => {
      this.user = user
      if(this.user != null) {
        console.log("User != null")
        //this.userSub
      }
    })
  }

  navigateToDetails(id: number, role: Role) {
    console.log('Navigate to details: ' + id.toString())
    console.log('Navigate to details: ' + role.toString())
  }

  log(input: string) {
    console.log(input)
  }

  searchUser(): void {
    return
  }

}
