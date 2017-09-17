import { Http } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { BaseService } from '../../shared/base.service';
import { Observable } from 'rxjs/Rx';
import { Snippet } from './snippet';
import { Injectable } from '@angular/core';

@Injectable()
export class SnippetService extends BaseService {

  snippetEndpoint = '/snippet';

  constructor(private http: Http) {
    super();
  }

  getSnippets(): Observable<Snippet[]> {
    return this.http.get(this.buildUrl(this.snippetEndpoint))
      .map(this.extractArray)
      .catch(this.extractError);
  }

  getSnippet(id: string): Observable<Snippet> {
    return this.http.get(this.buildUrl(this.snippetEndpoint) + '/' + id)
      .map(res => Snippet.toObject(this.extractObject(res)))
      .catch(this.extractError);
  }

  createSnippet(snippet: Snippet): Observable<Snippet> {
    return this.http.post(this.buildUrl(this.snippetEndpoint), snippet)
      .map(res => Snippet.toObject(this.extractObject(res)))
      .catch(this.extractError);
  }
}