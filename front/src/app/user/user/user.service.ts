import { BaseService } from '../../shared/base.service';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from './user';


@Injectable()
export class UserService extends BaseService {

  static userEndpoint = '/user';

  constructor(private http: Http) {
    super();
  }

  deleteUser(login: string) {
    return this.http.delete(this.buildUrl(UserService.userEndpoint + '/' + login))
      .map(this.extractObject)
      .catch(this.extractError);
  }

  getUser(login: string): Observable<User> {
    return this.http.get(this.buildUrl(UserService.userEndpoint + '/' + login))
      .map(this.extractObject)
      .catch(this.extractError)
  }

}
