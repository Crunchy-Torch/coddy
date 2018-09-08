import { BaseService } from '../../shared/base.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';


@Injectable()
export class UserService extends BaseService {

  static userEndpoint = '/user';

  constructor(private http: HttpClient) {
    super();
  }

  deleteUser(login: string) {
    return this.http.delete(this.buildUrl(UserService.userEndpoint + '/' + login))
      .pipe(
        catchError(this.extractError)
      );
  }

  getUser(login: string): Observable<User> {
    return this.http.get<User>(this.buildUrl(UserService.userEndpoint + '/' + login))
      .pipe(
        catchError(this.extractError)
      );
  }
}
