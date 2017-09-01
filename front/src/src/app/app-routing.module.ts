import { PrivateComponent } from './core/template/private/private.component';
import { PublicComponent } from './core/template/public/public.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const APP_ROUTES: Routes = [];

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
