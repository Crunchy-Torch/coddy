import { Injectable } from '@angular/core';
import { Error } from './error/error';
import { Observable } from 'rxjs/Rx';
import { environment } from '../../environments/environment';
import { HttpResponse } from "@angular/common/http";

@Injectable()
export class BaseService {

  private static DEFAULT_ERROR = 'Something went horribly wrong...';
  private static DEFAULT_DETAILS = 'A team of highly trained monkeys has been dispatched to deal with this situation.';

  protected extractError(res: HttpResponse<any>) {

    const error: Error = new Error();
    if (res instanceof HttpResponse) {
      // Extract error message
      error.status = res.status;
      error.message = res.statusText || BaseService.DEFAULT_ERROR;
      error.details = res.body.message || BaseService.DEFAULT_DETAILS;
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
