import { LoginComponent } from './login/login.component';
import { PublicComponent } from '../core/template/public/public.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const PUBLIC_ROUTES: Routes = [
  { path: 'login', component: LoginComponent }
];

const AUTH_ROUTES: Routes = [
  { path: '', component: PublicComponent, children: PUBLIC_ROUTES }
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
