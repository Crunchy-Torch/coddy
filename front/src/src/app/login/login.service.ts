import { Http } from '@angular/http';
import { BaseService } from '../shared/base.service';
import { Injectable } from '@angular/core';

@Injectable()
export class LoginService extends BaseService {

  authEndpoint = '/user/auth';

  constructor(private http: Http) {
    super();
  }

  authenticate(login: string, password: string) {

    let body: any = {
      login: login,
      password: password
    };
    return this.http.post(this.buildUrl(this.authEndpoint), body)
      .map(this.extractObject)
      .catch(this.extractError);
  }
}