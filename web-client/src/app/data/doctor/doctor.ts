import {UserDetails} from '../userDetails/user-details'


export class Doctor {
    id: number
    userDetailsId: UserDetails
    firstName: String
    lastName: String
    licenseCode: String

    constructor(){
        this.firstName=""
        this.lastName=""
        this.licenseCode=""
    }
}
