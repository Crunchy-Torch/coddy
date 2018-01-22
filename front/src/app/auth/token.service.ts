import { Token } from './token';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable()
export class TokenService {

  private token: Token = null;

  constructor(private jwtHelper: JwtHelperService) {
  }

  setToken(token: string) {
    localStorage.setItem(Token.TOKEN_KEY, token);
    this.token = this.decodeToken();
  }

  getToken(): Token {
    if (this.token === null) {
      this.token = this.decodeToken();
    }
    return this.token;
  }

  hasValidToken(): boolean {
    return this.hasToken() && this.hasNotExpired();
  }

  hasToken(): boolean {
    return this.getToken() !== null;
  }

  hasNotExpired(): boolean {
    // Token 'not before' and 'expiration time' are in seconds
    const now: number = (Date.now() / 1000) + 100; // Hack: add 100ms to prevent error due to token.nbf rounded value
    return this.token.exp > now && this.token.nbf < now;
  }

  clearToken(): void {
    this.token = null;
    localStorage.removeItem(Token.TOKEN_KEY);
  }

  private decodeToken(): Token {
    // Get token from local storage
    const item: string = localStorage.getItem(Token.TOKEN_KEY);

    // Check if token is set
    if (typeof item !== 'undefined' && item !== null && item.length !== 0 && item !== 'undefined') {
      // Extract and decode token
      const decodedToken: any = this.jwtHelper.decodeToken(item);
      // Create typed token object
      const token: Token = new Token();
      token.raw = item;
      token.firstname = decodedToken.firstname;
      token.lastname = decodedToken.lastname;
      token.login = decodedToken.login;
      token.exp = decodedToken.exp;
      token.nbf = decodedToken.nbf;
      decodedToken.permissions.forEach(permission => {
        token.permissions.add(Token.toEnum(permission));
      });
      return token;
    }
    return null;
  }
}
