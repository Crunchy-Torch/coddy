import { BaseService } from '../../core/base.service';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../user/user';
import { UserService } from '../user/user.service';

@Injectable()
export class AdminService extends BaseService {

  constructor(private http: Http) {
    super();
  }

  getUsers(from = 0, size = 10): Observable<User[]> {
    return this.http.get(this.buildUrl(UserService.userEndpoint) + "?from=" + from + "&size=" + size)
      .map(this.extractArray)
      .catch(this.extractError)
  }

  count(): Observable<number> {
    return this.http.get(this.buildUrl(UserService.userEndpoint + "/count"))
      .map(this.extractObject)
      .catch(this.extractError)
  }
}