import {Component, Input, OnInit} from '@angular/core';
import {FieldError} from "../../data/error/field-error";

@Component({
  selector: 'app-alert-window',
  templateUrl: './alert-window.component.html',
  styleUrls: ['./alert-window.component.css']
})
export class AlertWindowComponent implements OnInit {

  @Input() errors: Array<FieldError>

  constructor() { }

  ngOnInit(): void {
  }

}
