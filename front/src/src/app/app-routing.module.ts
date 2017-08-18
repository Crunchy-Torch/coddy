import { PrivateComponent } from './core/template/private/private.component';
import { PublicComponent } from './core/template/public/public.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './account/admin/admin.component';

const PRIVATE_ROUTES: Routes = [
  { path: 'admin', component: AdminComponent }
];

const APP_ROUTES: Routes = [
  { path: '', component: PrivateComponent, children: PRIVATE_ROUTES }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      APP_ROUTES
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
