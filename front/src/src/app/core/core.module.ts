import { AdminGuard } from './admin/admin.guard';
import { PrivateGuard } from './template/private/private.guard';
import { PrivateComponent } from './template/private/private.component';
import { PublicComponent } from './template/public/public.component';
import { AuthModule } from '../auth/auth.module';
import { SharedModule } from '../shared/shared.module';
import { CoreRoutingModule } from './core-routing.module';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [
    SharedModule,
    CoreRoutingModule
  ],
  exports: [],
  declarations: [
    PublicComponent,
    PrivateComponent,
    DashboardComponent,
    HomeComponent    
  ],
  providers: [
    PrivateGuard,
    AdminGuard
  ]
})
export class CoreModule { }
