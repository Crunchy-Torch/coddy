import { Router } from '@angular/router';
import { TokenService } from '../../../auth/token.service';
import { Component } from '@angular/core';

@Component({
    selector: 'app-private',
    templateUrl: 'private.component.html',
    styleUrls: ['private.component.scss']
})

export class PrivateComponent {
    constructor(private router: Router, private tokenService: TokenService) { }

    logout() {
      this.tokenService.clearToken();
      this.router.navigate(['/']);
    }
}