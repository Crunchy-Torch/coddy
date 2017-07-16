import { BaseService } from './../shared/base.service';
import { Observable } from 'rxjs/Rx';
import serverUrl from '../../conf';
import { Http } from '@angular/http';
import { Snippet } from './snippet';
import { Injectable } from '@angular/core';

@Injectable()
export class SnippetService extends BaseService {

    snippetEndpoint = '/snippet';

    constructor(private http: Http) {
        super();
    }

    getSnippets(): Observable<Snippet[]> {
        return this.http.get(serverUrl + this.snippetEndpoint)
            .map(this.extractArray)
            .catch(this.extractError);
    }

    getSnippet(id: string): Observable<Snippet> {
        return this.http.get(serverUrl + this.snippetEndpoint + '/' + id)
            .map(this.extractObject)
            .catch(this.extractError);
    }
}