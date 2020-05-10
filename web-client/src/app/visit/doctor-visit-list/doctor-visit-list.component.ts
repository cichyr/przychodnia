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
