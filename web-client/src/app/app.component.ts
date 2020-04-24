import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'web-client';

  constructor(private router: Router) {
  }


  navigateToHomePage(){
    this.router.navigate(['home'])
  }


}
