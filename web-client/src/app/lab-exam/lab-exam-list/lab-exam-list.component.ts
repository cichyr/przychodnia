import { Component, OnInit } from '@angular/core';
import { LaboratoryExaminationGeneral } from '../../data/examination/laboratory-examination-general'
import { LaboratoryExaminationService } from '../../service/laboratory-examination.service'
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/data/user/user';


@Component({
  selector: 'app-lab-exam-list',
  templateUrl: './lab-exam-list.component.html'
})
export class LabExamListComponent implements OnInit {

  laboratoryList: LaboratoryExaminationGeneral[]
  userSub: Subscription
  labSub: Subscription
  user: User

  filters = { 'in_progress': true, 'executed': true, 'cancelled': true, 'approved': true }

  // Constructor
  constructor(private laboratoryService: LaboratoryExaminationService, private userService: UserService, private router: Router) { }

  // Initialize values
  ngOnInit(): void {
    this.userSub = this.userService.getAuthenticationEvent().subscribe(user => {
      this.user = user
      if(this.user != null)
        this.labSub = this.laboratoryService.getAllLaboratoryExams().subscribe(labs => this.laboratoryList = labs)
    })

    this.userService.getAuthenticationEvent().subscribe(user => {
      this.user = user
      if(this.user != null)
        this.labSub = this.laboratoryService.getAllLaboratoryExams().subscribe(labs => this.laboratoryList = labs)
    })
  }

  // Sorting function
  sort(option: number): void {
    switch (option) {
      case 0:   // commision date newest first
        this.laboratoryList = this.laboratoryList.sort((l1, l2) => {
          if (l1.creationDate < l2.creationDate) {
            return 1
          }
          if (l1.creationDate > l2.creationDate) {
            return -1
          }
          return 0
        })
        break

      case 1:   // commision date oldest first
        this.laboratoryList = this.laboratoryList.sort((l1, l2) => {
          if (l1.creationDate > l2.creationDate) {
            return 1
          }
          if (l1.creationDate < l2.creationDate) {
            return -1
          }
          return 0
        })
        break

      case 2:   // finish date newest first
        this.laboratoryList = this.laboratoryList.sort((l1, l2) => {
          if (l1.approvalCancellationDate < l2.approvalCancellationDate) {
            return 1
          }
          if (l1.approvalCancellationDate > l2.approvalCancellationDate) {
            return -1
          }
          return 0
        })
        break

      case 3:   // finish date oldest first
        this.laboratoryList = this.laboratoryList.sort((l1, l2) => {
          if (l1.approvalCancellationDate > l2.approvalCancellationDate) {
            return 1
          }
          if (l1.approvalCancellationDate < l2.approvalCancellationDate) {
            return -1
          }
          return 0
        })
        break

      case 4:   // id lowest first
        this.laboratoryList = this.laboratoryList.sort((l1, l2) => l1.id - l2.id)
        break

      case 5:   // id highest first
        this.laboratoryList = this.laboratoryList.sort((l1, l2) => l2.id - l1.id)
        break

      case 6:   // default
        this.laboratoryList = this.laboratoryList
        break
    }
  }

  // navigate to Examination details view
  navigateToDetails(id: number) {
    this.router.navigate(['/exam-list/' + id])
  }

  // deconstructor
  ngOnDestroy(): void {
    if(this.userSub != null) {
      this.userSub.unsubscribe()
    }

    if(this.labSub != null) {
      this.labSub.unsubscribe()
    }
  }

}
