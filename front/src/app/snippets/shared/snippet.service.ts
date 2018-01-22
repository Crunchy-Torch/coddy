import { BaseService } from '../../shared/base.service';
import { Observable } from 'rxjs/Rx';
import { Snippet } from './snippet';
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable()
export class SnippetService extends BaseService {

  snippetEndpoint = '/snippet';

  constructor(private http: HttpClient) {
    super();
  }

  getSnippets(): Observable<Snippet[]> {
    return this.http.get<Snippet[]>(this.buildUrl(this.snippetEndpoint))
      .catch(this.extractError);
  }

  getSnippet(id: string): Observable<Snippet> {
    return this.http.get<Snippet>(this.buildUrl(this.snippetEndpoint) + '/' + id)
      .catch(this.extractError);
  }

  createSnippet(snippet: Snippet): Observable<Snippet> {
    return this.http.post<Snippet>(this.buildUrl(this.snippetEndpoint), snippet)
      .catch(this.extractError);
  }
}
