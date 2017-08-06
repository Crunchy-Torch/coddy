import { BaseService } from '../../shared/base.service';
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

  getUsers(): Observable<User[]> {
    return this.http.get(this.buildUrl(UserService.userEndpoint))
      .map(this.extractArray)
      .catch(this.extractError)
  }
}