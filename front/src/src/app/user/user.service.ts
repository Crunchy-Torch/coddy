import { BaseService } from '../shared/base.service';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from './user';
import serverUrl from '../../conf';

@Injectable()
export class UserService extends BaseService {

    userEndpoint = '/user';

    constructor(private http: Http) {
        super();
    }

    getUsers(): Observable<User[]> {
        return this.http.get(serverUrl + this.userEndpoint)
            .map(this.extractArray)
            .catch(this.extractError)
    }
}