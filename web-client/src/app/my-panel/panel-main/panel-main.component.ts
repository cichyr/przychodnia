import {Component, OnDestroy, OnInit} from '@angular/core';
import {HelloService} from '../../service/hello.service';
import {HelloResponse} from '../../dto/hello-response';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-panel-main',
  templateUrl: './panel-main.component.html',
  styleUrls: ['./panel-main.component.css']
})
export class PanelMainComponent implements OnInit, OnDestroy {

  someVariable = {
    "id": "3",
    "name": "Jarosław",
    "surename": "Paduch"
  } as Object;

  helloResponse: HelloResponse;
  someArray = ['qwe', 'abc', 'xyz'];
  subscription: Subscription;

  constructor(private helloService: HelloService) { }

  ngOnInit(): void {
    this.subscription = this.helloService.getHelloMessage().subscribe(
      response => this.helloResponse = response
    );
  }

  //WAZNE!!!
  //trzeba usunac suba bo inaczej bedzie wyciek pamieci
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
