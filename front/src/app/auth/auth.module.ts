import { AuthRoutingModule } from './auth-routing.module';
import { SharedModule } from '../shared/shared.module';
import { TokenService } from './token.service';
import { AuthHttpService } from './auth-http.service';
import { LoginService } from './login/login.service';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Http, RequestOptions } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { SignupComponent } from './signup/signup.component';

export function authHttpServiceFactory(http: Http, options: RequestOptions) {
  return new AuthHttpService(http, options);
}

@NgModule({
  imports: [
    SharedModule,
    AuthRoutingModule
  ],
  exports: [
    LoginComponent,
    SignupComponent
  ],
  declarations: [
    LoginComponent,
    SignupComponent
  ],
  providers: [
    LoginService,
    TokenService,
    {
      provide: AuthHttp,
      useFactory: authHttpServiceFactory,
      deps: [ Http, RequestOptions ]
    }
  ]
})
export class AuthModule {
}
