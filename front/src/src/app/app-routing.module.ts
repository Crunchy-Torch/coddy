import { PrivateGuard } from './../template/private/private.guard';
import { PrivateComponent } from './../template/private/private.component';
import { PublicComponent } from './../template/public/public.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './account/admin/admin.component';

const PUBLIC_ROUTES: Routes = [
  {path: '', component: HomeComponent}
];

const PRIVATE_ROUTES: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  {path: 'admin', component: AdminComponent}
];

const APP_ROUTES: Routes = [
  {path: '', component: PublicComponent, children: PUBLIC_ROUTES},
  {path: '', component: PrivateComponent, children: PRIVATE_ROUTES, canActivate: [ PrivateGuard ]}
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      APP_ROUTES
    )
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {
}
