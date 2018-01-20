import { BaseService } from '../../shared/base.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { User } from './user';
import { HttpClient } from "@angular/common/http";


@Injectable()
export class UserService extends BaseService {

  static userEndpoint = '/user';

  constructor(private http: HttpClient) {
    super();
  }

  deleteUser(login: string) {
    return this.http.delete(this.buildUrl(UserService.userEndpoint + '/' + login))
      .catch(this.extractError);
  }

  getUser(login: string): Observable<User> {
    return this.http.get(this.buildUrl(UserService.userEndpoint + '/' + login))
      .catch(this.extractError)
  }
}
