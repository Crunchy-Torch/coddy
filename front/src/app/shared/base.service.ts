import { Injectable } from '@angular/core';
import { Error } from './error/error';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable()
export class BaseService {

  private static DEFAULT_ERROR = 'Something went horribly wrong...';
  private static DEFAULT_DETAILS = 'A team of highly trained monkeys has been dispatched to deal with this situation.';

  protected extractError(res: HttpErrorResponse) {
    console.log(res);
    const error: Error = new Error();
    if (res instanceof HttpErrorResponse) {
      // Extract error message
      error.status = res.status;
      error.message = res.statusText || BaseService.DEFAULT_ERROR;
      error.details = res.error.message || BaseService.DEFAULT_DETAILS;
    } else {
      error.message = BaseService.DEFAULT_ERROR;
      error.details = res as string || BaseService.DEFAULT_DETAILS;
    }
    return Observable.throw(error);
  }

  protected buildUrl(endpoint: string): string {
    return environment.serverUrl + endpoint;
  }
}
