import { Permission } from '../../../auth/permission';
import { Token } from '../../../auth/token';
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

  @Input() token: Token = null;

  @Output() logout: EventEmitter<any> = new EventEmitter();

  constructor() { }

  signout(): void {
    this.logout.emit();
  }

  hasAccountPermission(): boolean {
    return this.token !== null && this.token.permissions.has(Permission.PERSONAL_ACCOUNT);
  }

  hasSnippetPermission(): boolean {
    return this.token !== null && this.token.permissions.has(Permission.PERSONAL_SNIPPET);
  }
}