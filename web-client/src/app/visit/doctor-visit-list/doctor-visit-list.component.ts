import { Component, OnInit } from '@angular/core';
import { Visit } from '../../data/visit/visit';
// Mock imports
import { DoctorVisitListService } from '../../service/doctor-visit-list.service'

@Component({
  selector: 'app-doctor-visit-list',
  templateUrl: './doctor-visit-list.component.html',
  styleUrls: ['./doctor-visit-list.component.css']
})
export class DoctorVisitListComponent implements OnInit {

  visitList:  Visit[]
  constructor(private visitService: DoctorVisitListService) { }

  ngOnInit(): void {
    this.visitList = this.visitService.getAllVisitsForDoctor();
  }

}
