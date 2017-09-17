import { Injectable } from '@angular/core';
import { Error } from './error/error';
import { Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { environment } from '../../environments/environment';

@Injectable()
export class BaseService {

  private static DEFAULT_ERROR = 'Something went horribly wrong...';

  protected extractArray(res: Response) {
    let body = res.json();
    return body || [];
  }

  protected extractObject(res: Response) {
    let body = res.json();
    return body || {};
  }

  protected extractError(res: Response) {

    let error: Error = new Error();
    if (res instanceof Response) {
      // Extract error message
      error.status = res.status
      error.message = res.statusText || BaseService.DEFAULT_ERROR;
      error.details = res.json().message || '';
    } else {
      error.message = BaseService.DEFAULT_ERROR;
      error.details = res as string || '';
    }
    return Observable.throw(error);
  }

  protected buildUrl(endpoint: string): string {
    return environment.serverUrl + endpoint;
  }
}