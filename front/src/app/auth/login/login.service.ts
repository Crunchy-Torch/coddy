import { Observable } from 'rxjs/Rx';
import { BaseService } from '../../shared/base.service';
import { Headers, Http, Response } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class LoginService extends BaseService {

  authEndpoint = '/user/auth';

  constructor(private http: Http) {
    super();
  }

  authenticate(login: string, password: string): Observable<string> {

    const headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.post(
      this.buildUrl(this.authEndpoint),
      JSON.stringify({ login: login, password: password }),
      { headers }
    ).map((res: any) => res.token.catch(this.extractError));
  }
}
