import { SharedModule } from '../shared/shared.module';
import { CoreModule } from './../core/core.module';
import { TokenService } from './token.service';
import { AuthHttpService } from './auth-http.service';
import { LoginService } from './login/login.service';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Http, RequestOptions } from '@angular/http';
import { AuthHttp, AuthConfig } from 'angular2-jwt';

export function authHttpServiceFactory(http: Http, options: RequestOptions) {
  return new AuthHttpService(http, options);
}

@NgModule({
  imports: [
    SharedModule
  ],
  exports: [
    LoginComponent
  ],
  declarations: [
    LoginComponent
  ],
  providers: [
    LoginService,
    TokenService,
    {
      provide: AuthHttp,
      useFactory: authHttpServiceFactory,
      deps: [Http, RequestOptions]
    }
  ]
})
export class AuthModule { }