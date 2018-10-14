import { Observable } from 'rxjs';
import { BaseService } from '../../shared/base.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';

@Injectable()
export class LoginService extends BaseService {

  authEndpoint = '/user/auth';

  constructor(private http: HttpClient) {
    super();
  }

  authenticate(login: string, password: string): Observable<string> {

    return this.http.post<any>(
      this.buildUrl(this.authEndpoint),
      JSON.stringify({ login: login, password: password })
    ).pipe(
      map(res => (res as any).token),
      catchError(this.extractError)
    );
  }
}
