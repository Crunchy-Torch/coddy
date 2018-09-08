import { BaseService } from '../../shared/base.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable()
export class LanguageService extends BaseService {

  snippetEndpoint = '/language';

  constructor(private http: HttpClient) {
    super();
  }

  getLanguages(): Observable<string[]> {
    return this.http.get<string[]>(this.buildUrl(this.snippetEndpoint))
      .pipe(
        catchError(this.extractError)
      );
  }
}
