import {Component, Input, OnInit} from '@angular/core';
import {FieldError} from "../../data/error/field-error";
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-alert-window',
  templateUrl: './alert-window.component.html',
  styleUrls: ['./alert-window.component.css']
})
export class AlertWindowComponent implements OnInit {

  @Input() errors: FieldError[]

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}
