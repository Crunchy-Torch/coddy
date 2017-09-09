import { Token } from './token';
import { Injectable } from '@angular/core';

@Injectable()
export class TokenService {

  private token: Token = null;

  constructor() { }

  setToken(token: string) {
    localStorage.setItem(Token.TOKEN_KEY, token);
    this.token = this.decodeToken();
  }

  getToken(): Token {
    if(this.token === null) {
      this.token = this.decodeToken();
    }
    return this.token;
  }

  hasValidToken(): boolean {
    return this.hasToken();
  }

  clearToken(): void {
    this.token = null;
    localStorage.removeItem(Token.TOKEN_KEY);
  }

  private decodeToken(): Token {
    // Get token from local storage
    let item: string = localStorage.getItem(Token.TOKEN_KEY);

    // TODO decode token properly
    this.token = new Token();
    this.token.raw = item;

    return this.token;
  }

  private hasToken(): boolean {
    return localStorage.getItem(Token.TOKEN_KEY) !== null;
  }
}