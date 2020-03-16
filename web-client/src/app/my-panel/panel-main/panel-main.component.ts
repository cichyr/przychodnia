import { Component, OnInit } from '@angular/core';
import {HelloService} from '../../service/hello.service';
import {HelloResponse} from '../../dto/hello-response';

@Component({
  selector: 'app-panel-main',
  templateUrl: './panel-main.component.html',
  styleUrls: ['./panel-main.component.css']
})
export class PanelMainComponent implements OnInit {

  someVariable = {
    "id": "3",
    "name": "Jarosław",
    "surename": "Paduch"
  } as Object;

  helloResponse: HelloResponse;
  someArray = ['qwe', 'abc', 'xyz'];

  constructor(private helloService: HelloService) { }

  ngOnInit(): void {
    this.helloService.getHelloMessage().subscribe(
      response => this.helloResponse = response
    );
  }

}
