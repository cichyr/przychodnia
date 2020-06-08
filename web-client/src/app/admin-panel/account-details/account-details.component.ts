import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/data/user/user';
import { Role } from 'src/app/data/user/role.enum';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { Subscription } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PasswordInputComponent } from 'src/app/modals/password-input/password-input.component';
import {DataExchangeService} from "../../service/data-exchange.service";

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  user_id: number
  role_id: number
  user: User
  logged_user: User
  userSub: Subscription

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute, private modalService: NgbModal, private dataExchange: DataExchangeService) { }

  ngOnInit(): void {
    this.user_id = Number(this.route.snapshot.paramMap.get('id'))
    this.role_id = Number(this.route.snapshot.paramMap.get('role_id'))

    this.userSub = this.userService.getAuthenticationEvent().subscribe(user => {
      this.logged_user = user
      if (this.logged_user != null) {
        this.userSub = this.userService.getUser(this.user_id, this.role_id).subscribe(user => this.user = user)
      }
    })

    this.userService.getAuthenticationEvent().subscribe(user => {
      this.logged_user = user
      if (this.logged_user != null) {
        this.userSub = this.userService.getUser(this.user_id, this.role_id).subscribe(user => this.user = user)
      }
    })
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

  navigateToList(): void {
    this.router.navigate(['/admin/user-list'])
  }

  openPopup() {
    const modalRef = this.modalService.open(PasswordInputComponent, { size: 'lg' })
    modalRef.result.then((result) => {
      this.changePassword(result)
    }, (reason) => {})
    modalRef.componentInstance.title = 'Zmiana hasła'
  }

  changePassword(new_hash: string): void {
    this.userSub = this.userService.updatePassword(this.user_id, this.role_id, new_hash).subscribe(user => this.user = user)
  }

  editUser(): void {
    this.dataExchange.setCurrentUser(this.user_id, this.role_id)

    this.router.navigate(['/admin/edit-user', this.user_id, this.role_id])
  }

}
