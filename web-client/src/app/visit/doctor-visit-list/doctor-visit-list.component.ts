import { Component, OnInit } from '@angular/core';
import { Visit } from '../../data/visit/visit';
// Mock imports
import { DoctorVisitListService } from '../../service/doctor-visit-list.service'
import {DoctorVisit} from "../../data/visit/doctor-visit";

@Component({
  selector: 'app-doctor-visit-list',
  templateUrl: './doctor-visit-list.component.html',
  styleUrls: ['./doctor-visit-list.component.css']
})
export class DoctorVisitListComponent implements OnInit {

  visitList: DoctorVisit[]

  constructor(private visitService: DoctorVisitListService) {
  }


  ngOnInit(): void {
    this.visitService.getVisits().subscribe(visits => this.visitList = visits);
    //this.visitList.sort((a, b) => (a.registrationDate.getDate() > b.registrationDate.getDate() ? 1 : -1))
  }
}
