import { SharedModule } from '../shared/shared.module';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import {JwtModule} from "@auth0/angular-jwt";
import {Token} from "./token";
import { TokenService } from "./token.service";
import { AuthRoutingModule } from "./auth-routing.module";
import { LoginService } from "./login/login.service";
import { environment } from "../../environments/environment";

export function tokenGetter() {
  return localStorage.getItem(Token.TOKEN_KEY);
}

@NgModule({
  imports: [
    SharedModule,
    AuthRoutingModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        throwNoTokenError: true,
        whitelistedDomains: [environment.serverUrl]
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
