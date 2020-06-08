import {Role} from "./role.enum";
export class User {

  id: number
  role: Role
  username: String
  password: String
  firstName: String
  lastName: String
  status: String
  licenseCode: String
  city: String
  streetAddress1: String
  streetAddress2: String
  zipCode: String
  region: String
  contactNumber: String

  constructor() {

    this.username = new String()
    this.password = new String()
    this.firstName = new String()
    this.lastName = new String()
    this.status = new String()
    this.licenseCode = new String()
    this.city = new String()
    this.streetAddress1 = new String()
    this.streetAddress2 = null
    this.zipCode = new String()
    this.region = new String()
    this.contactNumber = new String()
  }

}
