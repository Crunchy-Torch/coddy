import { BaseService } from '../../shared/base.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { User } from '../user/user';
import { UserService } from '../user/user.service';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable()
export class AdminService extends BaseService {

  constructor(private http: HttpClient) {
    super();
  }

  getUsers(from = 0, size = 10): Observable<User[]> {
    return this.http.get<User[]>(this.buildUrl(UserService.userEndpoint) + '?from=' + from + '&size=' + size)
      .pipe(
        catchError(this.extractError)
      );
  }

  count(): Observable<number> {
    return this.http.get<number>(this.buildUrl(UserService.userEndpoint + '/count'))
      .pipe(
        catchError(this.extractError)
      );
  }
}
