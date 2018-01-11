import { Http } from '@angular/http';
import { BaseService } from '../../shared/base.service';
import { Observable } from 'rxjs/Rx';
import { Injectable } from '@angular/core';

@Injectable()
export class LanguageService extends BaseService {

  snippetEndpoint = '/language';

  constructor(private http: Http) {
    super();
  }

  getLanguages(): Observable<string[]> {
    return this.http.get(this.buildUrl(this.snippetEndpoint))
      .map(this.extractArray)
      .catch(this.extractError);
  }
}
