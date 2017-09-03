import { Router } from '@angular/router';
import { TokenService } from '../../../auth/token.service';
import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: 'navbar.component.html',
  styleUrls: ['navbar.component.scss']
})

export class NavbarComponent {

  @Input() isLogged: boolean;

  @Input() isHomepage: boolean;

  @Output() logout: EventEmitter<any> = new EventEmitter();

  constructor() { }

  signout(): void {
    this.logout.emit();
  }
}