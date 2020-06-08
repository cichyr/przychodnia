import { Role } from './role.enum'

export class UserBasic {
    id: number
    role: Role
    username: string
    firstName: string
    lastName: string
    status: string
}
