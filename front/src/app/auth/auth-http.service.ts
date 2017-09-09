import { Token } from './token';
import { Http, RequestOptions } from '@angular/http';
import { AuthHttp, AuthConfig } from 'angular2-jwt';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthHttpService extends AuthHttp {

  constructor(http: Http, defOpts?: RequestOptions) {
    super(new AuthConfig({
      noTokenScheme: true,
      tokenName: Token.TOKEN_KEY,
      globalHeaders: [{ 'Content-Type': 'application/json' }]
    }), http, defOpts);
  }
}