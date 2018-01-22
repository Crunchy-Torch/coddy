import { BaseService } from '../../shared/base.service';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class LanguageService extends BaseService {

  snippetEndpoint = '/language';

  constructor(private http: HttpClient) {
    super();
  }

  getLanguages(): Observable<string[]> {
    return this.http.get<string[]>(this.buildUrl(this.snippetEndpoint))
      .catch(this.extractError);
  }
}
