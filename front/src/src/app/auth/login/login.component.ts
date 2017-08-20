import { Router } from '@angular/router';
import { TokenService } from '../token.service';
import { LoginService } from './login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  login = '';
  password = '';
  isLoading = false;
  error: Error;

  redirectTo = '/dashboard';

  constructor(
    private loginService: LoginService, 
    private tokenService: TokenService,
    private router: Router
  ) { }

  authenticate() {
    this.isLoading = true;
    this.error = null;

    this.loginService.authenticate(this.login, this.password).finally(
      () => this.isLoading = false
    ).subscribe(
      token => {
        this.tokenService.setToken(token);
        this.router.navigate([this.redirectTo]);
      },
      err => this.error = err
    );
  }
}
