import { AuthHttp } from 'angular2-jwt';
import { BaseService } from '../../shared/base.service';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../user/user';
import { UserService } from '../user/user.service';

@Injectable()
export class AdminService extends BaseService {

  constructor(private authHttp: AuthHttp) {
    super();
  }

  getUsers(from = 0, size = 10): Observable<User[]> {
    return this.authHttp.get(this.buildUrl(UserService.userEndpoint) + "?from=" + from + "&size=" + size)
      .map(this.extractArray)
      .catch(this.extractError)
  }

  count(): Observable<number> {
    return this.authHttp.get(this.buildUrl(UserService.userEndpoint + "/count"))
      .map(this.extractObject)
      .catch(this.extractError)
  }
}