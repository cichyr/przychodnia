import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-text-input',
  templateUrl: './text-input.component.html',
  styleUrls: ['./text-input.component.css']
})
export class TextInputComponent implements OnInit {

  // Variable holding Modal title
  @Input() public title: string = "Uzupełnij '@Input() title'"

  // Variable holding initial textarea input (e.g. for editing previous texts), can be left empty
  @Input() public preInput: string

  // Textarea value
  public text: string

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void { }

  // Function which assigns textarea value to correct variable
  enterText(input: string) {
    this.text = input
  }

}
