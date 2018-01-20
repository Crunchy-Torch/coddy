import { Observable } from 'rxjs/Rx';
import { BaseService } from '../../shared/base.service';
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable()
export class LoginService extends BaseService {

  authEndpoint = '/user/auth';

  constructor(private http: HttpClient) {
    super();
  }

  authenticate(login: string, password: string): Observable<string> {

    return this.http.post(
      this.buildUrl(this.authEndpoint),
      JSON.stringify({ login: login, password: password })
    ).map((res: any) => res.token.catch(this.extractError));
  }
}
