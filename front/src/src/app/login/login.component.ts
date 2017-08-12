import { TokenService } from './../shared/token.service';
import { LoginService } from './login.service';
import { Error } from '../shared/error/error';
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
