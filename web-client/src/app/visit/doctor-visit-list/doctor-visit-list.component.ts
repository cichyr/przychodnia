import { Component, OnInit, OnDestroy } from '@angular/core';
import { DoctorVisitListService } from '../../service/doctor-visit-list.service'
import {DoctorVisit} from "../../data/visit/doctor-visit";
import {Subscription} from "rxjs";
import {User} from "../../data/user/user";
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-doctor-visit-list',
  templateUrl: './doctor-visit-list.component.html',
  styleUrls: ['./doctor-visit-list.component.css']
})
export class DoctorVisitListComponent implements OnInit {

  visitList: DoctorVisit[]
  __visitList: DoctorVisit[]
  user: User
  userSub: Subscription
  visitSub: Subscription

  constructor(private visitService: DoctorVisitListService, private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {

    this.userSub =
      this.userService.getAuthenticationEvent().subscribe(user => {
          this.user = user;
          if (this.user != null)
            this.visitSub = this.visitService.getVisits(user.id).subscribe(visits => this.visitList = visits);
        }
      );

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

  filters = { 'finished': true, 'appointed': true, 'cancelled': true }

  // Sorting function
  sort(option: number): void {

      if(option == 0) {
        this.visitList .sort((v1, v2) => {
          if (v1.registrationDate > v2.registrationDate) {
            return 1
          } else if (v1.registrationDate < v2.registrationDate) {
            return -1
          }
          return 0
        })
      }
    else if(option == 1) {
      this.visitList.sort((v1, v2) => {
        if (v1.registrationDate < v2.registrationDate) {
          return 1
        } else if (v1.registrationDate > v2.registrationDate) {
          return -1
        } else
          return 0
      })
    }
    else if(option == 2) {
      this.visitList.sort((v1, v2) => {
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
        this.visitList.sort((v1, v2) => {
          if (v1.state.id > v2.state.id) {
            return 1
          } else if (v1.state.id < v2.state.id) {
            return -1
          }
          return 0
        })
      }

    else if(option == 4) {
      this.visitList.sort((v1, v2) => v1.id - v2.id)
    }
    else if(option == 5){
        this.visitList.sort((v1, v2) => v2.id - v1.id)
      }
  }

  navigateToDetails(id: number) {
    this.router.navigate(['/visit-details/' + id]);
  }

  ngOnDestroy(): void {
    if (this.userSub != null)
      this.userSub.unsubscribe()

    if(this.visitSub != null)
      this.visitSub.unsubscribe()
  }
}
