import { Component, OnInit } from '@angular/core';
import {ReceptionistVisit} from "../../data/visit/receptionist-visit";
import { ReceptionistVisitListService } from '../../service/receptionist-visit-list.service'

@Component({
  selector: 'app-receptionist-visit-list',
  templateUrl: './receptionist-visit-list.component.html',
  styleUrls: ['./receptionist-visit-list.component.css']
})
export class ReceptionistVisitListComponent implements OnInit {

visitList: ReceptionistVisit[]

  constructor(private visitService: ReceptionistVisitListService) { }

  ngOnInit(): void {
  this.visitService.getVisits().subscribe(visits => this.visitList = visits);

  }



}
