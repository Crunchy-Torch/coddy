import { LoginComponent } from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const AUTH_ROUTES: Routes = [
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(AUTH_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class AuthRoutingModule { }
