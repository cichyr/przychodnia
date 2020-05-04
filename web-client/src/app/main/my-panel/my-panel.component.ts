import { Component, OnInit } from '@angular/core';
import {faMicroscope, faNotesMedical} from '@fortawesome/free-solid-svg-icons'
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-my-panel',
  templateUrl: './my-panel.component.html',
  styleUrls: ['./my-panel.component.css']
})
export class MyPanelComponent implements OnInit {

  microscope = faMicroscope
  visit = faNotesMedical

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
  }

  getRole(): string {
    return this.userService.getUserRole()
  }

  navigateToLab() {
    if(this.getRole() == 'LABW' || this.getRole() == 'LABS') {
      this.router.navigate(['/exam-list/'])
    }
  }

  navigateToVisit() {
    if(this.getRole() == 'REC') {
      this.router.navigate(['/receptionist-visit-list/'])
    }
    else if(this.getRole() == 'DOC') {
      this.router.navigate(['/doctor-visit-list/'])
    }    
  }

}
