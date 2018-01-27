import { SharedModule } from '../shared/shared.module';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { JWT_OPTIONS, JwtModule } from '@auth0/angular-jwt';
import { Token } from './token';
import { TokenService } from './token.service';
import { AuthRoutingModule } from './auth-routing.module';
import { LoginService } from './login/login.service';
import { environment } from '../../environments/environment';

export function tokenGetter() {
  return localStorage.getItem(Token.TOKEN_KEY);
}

export function getHostName(url) {
  const match = url.match(/:\/\/(www[0-9]?\.)?(.[^/]+)/i);
  if (match != null && match.length > 2 && typeof match[2] === 'string' && match[2].length > 0) {
    return match[2];
  } else {
    return '';
  }
}

export function jwtOptionsFactory() {
  return {
    tokenGetter: tokenGetter,
    throwNoTokenError: false,
    whitelistedDomains: [getHostName(environment.serverUrl)]
  };
}

@NgModule({
  imports: [
    SharedModule,
    AuthRoutingModule,
    JwtModule.forRoot({
      jwtOptionsProvider: {
        provide: JWT_OPTIONS,
        useFactory: jwtOptionsFactory
      }
    })
  ],
  exports: [
    LoginComponent
  ],
  declarations: [
    LoginComponent
  ],
  providers: [
    TokenService,
    LoginService
  ]
})
export class AuthModule {
}
