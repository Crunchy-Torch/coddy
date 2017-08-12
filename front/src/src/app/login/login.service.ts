import { Http, Headers } from '@angular/http';
import { BaseService } from '../shared/base.service';
import { Injectable } from '@angular/core';

@Injectable()
export class LoginService extends BaseService {

  authEndpoint = '/user/auth';

  constructor(private http: Http) {
    super();
  }

  authenticate(login: string, password: string) {

    let headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.post(
      this.buildUrl(this.authEndpoint),
      JSON.stringify({ login: login, password: password }),
      { headers }
    ).map(this.extractObject).catch(this.extractError);
  }
}