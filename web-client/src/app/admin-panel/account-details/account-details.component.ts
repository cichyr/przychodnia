import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/data/user/user';
import { Role } from 'src/app/data/user/role.enum';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  constructor() { }

  user: User = {
    id: 0,
    username: 'username',
    role: Role.DOCTOR,
    firstName: 'firstName',
    lastName: 'lastName',
    status: 'ENABLED',
    licenseCode: 'licenseCode',
    city: 'city',
    streetAddress1: 'streetAddress1',
    streetAddress2: 'streetAddress2',
    zipCode: 'zipCode',
    region: 'region',
    contactNumber: 'contactNumber',
  }

  ngOnInit(): void {
  }

  navigateToList(): void {
    return
  }

  changePassword(): void {
    return
  }

  editUser(): void {
    return
  }

}
