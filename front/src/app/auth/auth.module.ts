import { SharedModule } from '../shared/shared.module';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import {JwtModule} from "@auth0/angular-jwt";
import {Token} from "./token";

export function tokenGetter() {
  return localStorage.getItem(Token.TOKEN_KEY);
}

@NgModule({
  imports: [
    SharedModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        //globalHeaders: [ {'Content-Type': 'application/json'} ],
        throwNoTokenError: true,
        whitelistedDomains: ['localhost:3001']
      }
    })
  ],
  exports: [
    LoginComponent
  ],
  declarations: [
    LoginComponent
  ]
})
export class AuthModule {
}
