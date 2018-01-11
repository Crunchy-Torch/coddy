import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  appName = 'Coddy';
  appDescription = 'Code snippets warehouse.';

  constructor() { }

  public scrollToLogin() {
    const element: Element = document.getElementById('coddy-presentation');
    element.scrollIntoView(true);
  }
}
