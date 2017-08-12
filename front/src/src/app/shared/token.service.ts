import { Token } from './token';
import { Injectable } from '@angular/core';

@Injectable()
export class TokenService {

  private tokenName = 'authToken';
  private token: Token = null;

  constructor() { }

  setToken(token: string) {
    localStorage.setItem(this.tokenName, token);
    this.token = this.decodeToken();
  }

  getToken(): Token {
    if(this.token === null) {
      this.token = this.decodeToken();
    }
    return this.token;
  }

  hasToken(): boolean {
    return this.getToken() !== null;
  }

  clearToken(): void {
    this.token = null;
    localStorage.removeItem(this.tokenName);
  }

  private decodeToken(): Token {
    // Get token from local storage
    let item: string = localStorage.getItem(this.tokenName);

    // TODO decode token properly
    this.token = new Token();
    this.token.raw = item;

    return this.token;
  }
}