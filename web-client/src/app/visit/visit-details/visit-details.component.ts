import { Component, OnInit, OnDestroy } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {VisitDetailsService} from "../../service/visit-details.service";
import {User} from "../../data/user/user";
import {Subscription} from "rxjs";
import {UserService} from "../../service/user.service";
import {VisitDetails} from "../../data/visit/visit-details";

@Component({
  selector: 'app-visit-details',
  templateUrl: './visit-details.component.html',
  styleUrls: ['./visit-details.component.css']
})
export class VisitDetailsComponent implements OnInit {

  visit: VisitDetails
  visitId: number
  user: User
  userSub: Subscription
  visitSub: Subscription

  constructor(private router: Router, private visitService: VisitDetailsService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.visitId = Number(this.route.snapshot.paramMap.get('id'));
    this.userSub =
      this.userService.getAuthenticationEvent().subscribe(user => {
          this.user = user;
          if (this.user != null)
            this.visitSub = this.visitService.getVisit(this.visitId).subscribe(_visit => this.visit = _visit);
        }
      );
  }

  finalizeVisit(): void{
    this.visitSub = this.visitService.finalizeVisit(this.visit.visitId).subscribe(visit => this.visit = visit);
  }

  cancelVisit(): void{
    this.visitSub = this.visitService.cancelVisit(this.visit.visitId).subscribe(visit => this.visit = visit);
  }

  navigateToDoctorVisitList(): void {
    this.router.navigate(['doctor-visit-list']);
  }

  ngOnDestroy(): void
  {
    if (this.userSub != null)
      this.userSub.unsubscribe()

    if(this.visitSub != null)
      this.visitSub.unsubscribe()
  }
}
