import { Component, OnInit } from '@angular/core';
import {faMicroscope} from '@fortawesome/free-solid-svg-icons'
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-panel',
  templateUrl: './my-panel.component.html',
  styleUrls: ['./my-panel.component.css']
})
export class MyPanelComponent implements OnInit {

  microscope = faMicroscope

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  navigateToLab() {
    this.router.navigate(['/exam-list/'])
  }

}
