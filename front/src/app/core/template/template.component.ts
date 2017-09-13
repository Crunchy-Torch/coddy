import { Token } from '../../auth/token';
import { Router } from '@angular/router';
import { TokenService } from '../../auth/token.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-template',
  templateUrl: 'template.component.html'
})

export class TemplateComponent implements OnInit {
  constructor(private tokenService: TokenService, private router: Router) { }

  ngOnInit() { }

  getToken(): Token {
    return this.tokenService.getToken();
  }

  isLogged(): boolean {
    return this.tokenService.hasValidToken();
  }

  isHomepage(): boolean {
    return this.router.url === '/';
  }

  logout() {
    this.tokenService.clearToken();
    this.router.navigate(['/']);
  }

  isLoginPage(): boolean {
    return this.router.url === '/login';
  }
}