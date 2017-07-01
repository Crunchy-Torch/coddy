import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class AppService {

  url = 'http://localhost:8080/hello'

  constructor(private http: Http) { }
  
  sayHello(): Observable<string> {

    return this.http.get(this.url).map(this.extractValue).catch(this.extractError);
  }

  extractValue(res: Response) {
    let body = (res as any)._body;
    return body || 'No text';
  }

  protected extractError(res: Response) {
    return Observable.throw('Error');
  }
}