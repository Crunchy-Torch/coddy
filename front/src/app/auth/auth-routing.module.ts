import { LoginComponent } from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { SignupComponent } from "./signup/signup.component";

const AUTH_ROUTES: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(AUTH_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class AuthRoutingModule {
}
