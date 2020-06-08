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

  komunikat: String = new String()
  user: User = new User()
  confirmPassword: String = new String()
  role: String

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

  checkFields(): boolean {

    if (this.user.username.length == 0 || this.user.username == "")
      return false;
    else if (this.user.password.length == 0 || this.user.password == "")
      return false;
    else if (this.user.firstName.length == 0 || this.user.firstName == "")
      return false;
    else if (this.user.lastName.length == 0 || this.user.lastName == "")
      return false;
    else if (this.user.licenseCode.length == 0 || this.user.licenseCode == "")
      return false;
    else if (this.user.streetAddress1.length == 0 || this.user.streetAddress1 == "")
      return false;
    else if (this.user.city.length == 0 || this.user.city == "")
      return false;
    else if (this.user.zipCode.length == 0 || this.user.zipCode == "")
      return false;
    else if (this.user.region.length == 0 || this.user.region == "")
      return false;
    else if (this.user.contactNumber.length == 0 || this.user.contactNumber == "")
      return false;
    else
      return true;
  }

  confirmNewUser(): void {

    if(this.checkPassword())
    {
      this.userService.postNewUser(this.user).subscribe(user => {
        this.user = user;
      });

      console.log(this.user)

      // let roleNumber: number;
      //
      // if (this.user.role == "DOC")
      //   roleNumber = 1;
      // else if (this.user.role == "REC")
      //   roleNumber = 2;
      // else if (this.user.role == "LABS")
      //   roleNumber = 3;
      // else if (this.user.role == "LABW")
      //   roleNumber = 4;
      // else if (this.user.role == 'ADMIN')
      //   roleNumber = 5;
      //
      // this.router.navigate([`admin/user-list`, this.user.id, roleNumber])
    }
    else
    {
      this.komunikat = "dupa!"
      this.displayAlert()
    }

  }

  displayAlert(): void {

  }
}
