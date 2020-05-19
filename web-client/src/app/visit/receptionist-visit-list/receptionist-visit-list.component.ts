import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router'
import {ReceptionistVisit} from "../../data/visit/receptionist-visit";
import {ReceptionistVisitListService} from '../../service/receptionist-visit-list.service'
import {User} from "../../data/user/user";
import {UserService} from "../../service/user.service";
import {Subscription} from "rxjs";
import {interval} from "rxjs";
import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";


@Component({
  selector: 'app-receptionist-visit-list',
  templateUrl: './receptionist-visit-list.component.html',
  styleUrls: ['./receptionist-visit-list.component.css']
})
export class ReceptionistVisitListComponent implements OnInit, OnDestroy {

  visitList: ReceptionistVisit[]
  __visitList: ReceptionistVisit[]
  user: User
  userSub: Subscription
  visitSub: Subscription
  cancelVisitSub: Subscription
  cancelButtonPressed = false

  constructor(private router: Router, private visitService: ReceptionistVisitListService, private userService: UserService) {
    setInterval(() => this.closeAlert(), 8000);
  }

  ngOnInit(): void {
    this.userSub =
      this.userService.getAuthenticationEvent().subscribe(user => {
          this.user = user;
          if (this.user != null)
            this.visitSub = this.visitService.getVisits(user.id).subscribe(visits => this.visitList = visits);
        }
      );

    this.userService.getAuthenticationEvent().subscribe(user => {
        this.user = user;
        if (this.user != null)
          this.visitSub = this.visitService.getVisits(user.id).subscribe(visits => this.__visitList = visits);
      }
    );

  }

  filters = { 'finished': true, 'appointed': true, 'cancelled': true }

  // Sorting function
  sort(option: number): void {

    if(option == 0) {
      this.visitList = this.__visitList.sort((v1, v2) => {
        if (v1.registrationDate > v2.registrationDate) {
          return 1
        } else if (v1.registrationDate < v2.registrationDate) {
          return -1
        }
        return 0
      })
    }
    else if(option == 1) {
      this.visitList = this.__visitList.sort((v1, v2) => {
        if (v1.registrationDate < v2.registrationDate) {
          return 1
        } else if (v1.registrationDate > v2.registrationDate) {
          return -1
        } else
          return 0
      })
    }
    else if(option == 2) {
      this.visitList = this.__visitList.sort((v1, v2) => {
        if (v1.state.id < v2.state.id) {
          return 1
        }
        if (v1.state.id > v2.state.id) {
          return -1
        }
        return 0
      })
    }
    else if(option == 3) {
      this.visitList = this.__visitList.sort((v1, v2) => {
        if (v1.state.id > v2.state.id) {
          return 1
        } else if (v1.state.id < v2.state.id) {
          return -1
        }
        return 0
      })
    }

    else if(option == 4) {
      this.visitList = this.__visitList.sort((v1, v2) => v1.id - v2.id)
    }
    else if(option == 5){
      this.visitList = this.__visitList.sort((v1, v2) => v2.id - v1.id)
    }
  }

  cancelVisit(id: number): void {
    let index: number;

    for(let i = 0; i < this.visitList.length; i++) {
      if(this.visitList[i].id == id)
        index = i;
    }

    this.cancelVisitSub = this.visitService.cancelVisit(id).subscribe(visit => {
      let receptionistVisit : ReceptionistVisit = this.visitService.mapVisitToReceptionistVisit(visit);
      this.visitList[index] = receptionistVisit;
     });

    this.cancelButtonPressed = true;
  }

  closeAlert(): void {
    this.cancelButtonPressed = false;
  }

  navigateToAddVisit() {
      this.router.navigate(['/add-visit'])
  }

  ngOnDestroy(): void {
    if (this.userSub != null)
      this.userSub.unsubscribe()

    if(this.visitSub != null)
      this.visitSub.unsubscribe()

    if(this.cancelVisitSub != null)
      this.visitSub.unsubscribe()
  }
}
