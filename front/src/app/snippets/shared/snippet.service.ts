import { BaseService } from '../../shared/base.service';
import { Observable } from 'rxjs';
import { Snippet } from './snippet';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Page } from '../../shared/structure/page';
import { URL } from '../../shared/structure/url';

@Injectable()
export class SnippetService extends BaseService {

  snippetEndpoint = '/snippet';

  constructor(private http: HttpClient) {
    super();
  }

  getSnippets(word?: string, from: number = 0, size: number = 10): Observable<Page<Snippet>> {
    const url = new URL()
      .setUri(this.snippetEndpoint)
      .addParameter('from', from)
      .addParameter('size', size);

    if (word && word !== '') {
      url.addParameter('query', word);
    }

    return this.http.get<Snippet[]>(url.buildUrl())
      .pipe(
        catchError(this.extractError)
      );
  }

  getSnippet(id: string): Observable<Snippet> {
    return this.http.get<Snippet>(this.buildUrl(this.snippetEndpoint) + '/' + id)
      .pipe(
        catchError(this.extractError)
      );
  }

  createSnippet(snippet: Snippet): Observable<Snippet> {
    return this.http.post<Snippet>(this.buildUrl(this.snippetEndpoint), snippet)
      .pipe(
        catchError(this.extractError)
      );
  }
}
