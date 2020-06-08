import {Component, OnDestroy, OnInit} from '@angular/core'
import {Router} from '@angular/router'
import {Subscription} from 'rxjs'
import {UserService} from './service/user.service'
import {faMicroscope, faNotesMedical, faThLarge, faUser, faUsersCog} from '@fortawesome/free-solid-svg-icons'
import {ErrorNotificationService} from "./service/error-notification.service";
import {FieldError} from "./data/error/field-error";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertWindowComponent} from "./modals/alert-window/alert-window.component";
import {PasswordInputComponent} from "./modals/password-input/password-input.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'web-client'
  authSubscription: Subscription
  errorNotificationSubscription: Subscription
  userFullName: String

  // icon variables
  microscope = faMicroscope
  visit = faNotesMedical
  menu = faThLarge
  login = faUser
  admin = faUsersCog

  constructor(
    private router: Router,
    private userService: UserService,
    private errorNotificationService: ErrorNotificationService,
    private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.authSubscription = this.userService.getAuthenticationEvent().subscribe(
      user => {
        if (user != null) this.userFullName = user.firstName + ' ' + user.lastName
        else this.userFullName = null
      })

    this.errorNotificationSubscription = this.errorNotificationService.getFieldErrors().subscribe(
      fieldErrors => this.showFieldErrors(fieldErrors)
    )
  }

  navigateToHomePage() {
    this.router.navigate(['home'])
  }

  navigateToLoginPage() {
    this.router.navigate(['login-page'])
  }

  navigateToUserDetails() {
    this.router.navigate(['user-details'])
  }

  navigateToLab() {
    if (this.getRole() == 'LABW' || this.getRole() == 'LABS' || this.getRole() === 'ADMIN') {
      this.router.navigate(['/exam-list/'])
    }
  }

  navigateToRecVisitList() {
    if (this.getRole() == 'REC') {
      this.router.navigate(['/receptionist-visit-list/'])
    }
  }

  navigateToVisit() {
    if (this.getRole() == 'REC') {
      this.router.navigate(['/receptionist-visit-list/'])
    } else if (this.getRole() == 'DOC') {
      this.router.navigate(['/doctor-visit-list/'])
    }
  }

  navigateToUserList() {
    this.router.navigate(['/admin/user-list'])
  }

  navigateToEditUserDEBUG_FUN() {
    this.router.navigate(['admin/edit-user'])
  }

  signOut() {
    this.userService.signOut()
    this.navigateToLoginPage()
  }

  getRole(): string {
    return this.userService.getUserRole()
  }

  ngOnDestroy(): void {
    if (this.authSubscription != null) this.authSubscription.unsubscribe()
    if (this.errorNotificationSubscription != null) this.errorNotificationSubscription.unsubscribe()
  }

  private showFieldErrors(errors: FieldError[]) {
    errors.forEach(error => error.translate())
    const modalRef = this.modalService.open(AlertWindowComponent, {size: "lg"})
    modalRef.componentInstance.errors = errors
  }

}
