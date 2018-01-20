import { BaseService } from '../../shared/base.service';
import { Observable } from 'rxjs/Rx';
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable()
export class LanguageService extends BaseService {

  snippetEndpoint = '/language';

  constructor(private http: HttpClient) {
    super();
  }

  getLanguages(): Observable<string[]> {
    return this.http.get(this.buildUrl(this.snippetEndpoint))
      .catch(this.extractError);
  }
}
