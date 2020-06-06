import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { MustMatch } from './must-match.validator'

@Component({
  selector: 'app-password-input',
  templateUrl: './password-input.component.html',
  styleUrls: ['./password-input.component.css']
})
export class PasswordInputComponent implements OnInit {

  @Input() public title: string = "Uzupełnij '@Input() title'"

  pwdForm: FormGroup
  public password: string
  sub = false

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.pwdForm = this.formBuilder.group({
      passwd: ['', Validators.required],
      confirmPasswd: ['', Validators.required]
    }, {
      validator: MustMatch('passwd', 'confirmPasswd')
    })
  }

  get f() { return this.pwdForm.controls }

  submit(input: string): void {
    this.sub = true

    if (this.pwdForm.invalid) {
      return
    }

    this.activeModal.close(input)
  }

}
