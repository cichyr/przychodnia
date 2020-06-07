import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/data/user/user';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';
import { Role } from 'src/app/data/user/role.enum';
import { faSearch } from '@fortawesome/free-solid-svg-icons'
import { UserBasic } from 'src/app/data/user/user-basic';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  filters = {'ENABLED': true, 'DISABLED': true}
  userList: UserBasic[]
  user: User
  userSub: Subscription
  search = faSearch
  seekedUser: User = new User()

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    // Subscription
    this.userSub = this.userService.getAuthenticationEvent().subscribe(user => {
      this.user = user
      if (this.user != null) {
        this.userSub = this.userService.getUserList().subscribe(users => this.userList = users)
      }
    })

    this.userService.getAuthenticationEvent().subscribe(user => {
      this.user = user
      if (this.user != null) {
        this.userSub = this.userService.getUserList().subscribe(users => this.userList = users)
      }
    })
  }

  navigateToAddUser() {
    this.router.navigate(['/admin/add-user'])
  }

  navigateToDetails(id: number, role: string) {
    this.router.navigate([`/admin/user-details/${id}/${role}`])
  }

  translateRole(role: string): string {
    switch (role) {
      case 'DOC':
        return 'Doktor'
      case 'LABS':
        return 'Kierownik laboratorium'
      case 'LABW':
        return 'Pracownik laboratorium'
      case 'REC':
        return 'Recepcjonista'
      case 'ADMIN':
        return 'Administrator'
    }
  }

  searchUser(): void {
    this.userSub = this.userService.getUserList(this.seekedUser).subscribe(users => this.userList = users)
  }

}
