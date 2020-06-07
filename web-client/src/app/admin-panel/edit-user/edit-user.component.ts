import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Subscription} from "rxjs";
import {User} from "../../data/user/user";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  userSub: Subscription
  EditUserSub: Subscription
  user: User

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userSub = this.userService.getAuthenticationEvent().subscribe(
      user => {this.user = user}
    )

  }



}
