import { Component, OnInit } from '@angular/core';
import {User} from "../../data/user/user";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  user: User
  confirmPassword: String
  role: String

  constructor() { }

  ngOnInit(): void {
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

}
