import {UserDetails} from '../userDetails/user-details'


export class Patient {
    id: number
    userDetailsId: UserDetails
    firstName: String
    lastName: String
    peselNumber: String

    constructor(){
        this.firstName=""
        this.lastName=""
        this.peselNumber=""
    }
}
