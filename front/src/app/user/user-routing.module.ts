import { PrivateGuard } from '../core/guard/private.guard';
import { AdminGuard } from '../core/guard/admin.guard';
import { AdminComponent } from './admin/admin.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const USER_ROUTES: Routes = [
  { path: 'admin', component: AdminComponent, canActivate: [PrivateGuard, AdminGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(USER_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class UserRoutingModule { }
