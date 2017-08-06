import { Error } from './error/error';
import { Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import serverUrl from '../../conf';

export class BaseService {

    protected extractArray(res: Response) {
        let body = res.json();
        return body || [];
    }

    protected extractObject(res: Response) {
        let body = res.json();
        return body || {};
    }

    protected extractError(res: Response) {
        console.log(res);
        let error: Error = new Error(res.status, res.statusText || 'Something went horribly wrong...');
        return Observable.throw(error);
    }

    protected buildUrl(endpoint: string): string{
        return serverUrl + endpoint;
    }
}