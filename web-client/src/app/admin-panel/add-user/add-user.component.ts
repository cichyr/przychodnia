import { Component, OnInit } from '@angular/core';
import {User} from "../../data/user/user";
import {UserService} from "../../service/user.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  userSub: Subscription
  logged_user: User
  komunikat: String = new String()
  user: User = new User()
  confirmPassword: String = new String()
  role: String
  IncorrectPassword: boolean

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  log(input: string) {
    this.user.username = input
    console.log(this.user.username)
  }

  checkPassword(): boolean {
    if(this.user.password === this.confirmPassword)
      return true;
    else
      return false;
  }

  confirmNewUser(): void {

    if(this.checkPassword())
    {
      this.userSub = this.userService.postNewUser(this.user).subscribe(user => {
        this.user = user;
        this.router.navigate([`admin/user-list`, this.user.id, roleNumber])
      });

      console.log(this.user)

      let roleNumber: number;

      if (this.user.role == "DOC")
        roleNumber = 1;
      else if (this.user.role == "REC")
        roleNumber = 2;
      else if (this.user.role == "LABS")
        roleNumber = 3;
      else if (this.user.role == "LABW")
        roleNumber = 4;
      else if (this.user.role == 'ADMIN')
        roleNumber = 5;
    }
    else
    {
      this.displayAlert()
    }

  }

  displayAlert(): void {
    this.IncorrectPassword = true;
  }

  goBack(): void {
    this.router.navigate(['admin/user-list'])
  }
}
