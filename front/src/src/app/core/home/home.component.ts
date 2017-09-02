import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  appName = 'Coddy';
  appDescription = 'Code snippets warehouse.'

  constructor() { }

  public scrollToLogin() {
    let element: Element = document.getElementById('coddy-presentation');
    element.scrollIntoView(true);
  }
}
