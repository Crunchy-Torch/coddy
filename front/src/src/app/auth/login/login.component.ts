import { TokenService } from '../token.service';
import { LoginService } from './login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  login = '';
  password = '';
  isLoading = false;
  error: Error;

  constructor(private loginService: LoginService, private tokenService: TokenService) { }

  authenticate() {
    this.isLoading = true;
    this.error = null;

    this.loginService.authenticate(this.login, this.password).finally(
      () => this.isLoading = false
    ).subscribe(
      token => this.tokenService.setToken(token),
      err => this.error = err
    );
  }
}
