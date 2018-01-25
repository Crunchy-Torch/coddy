import { ToastService } from './template/toast.service';
import { AdminGuard } from './guard/admin.guard';
import { PrivateGuard } from './guard/private.guard';
import { NavbarComponent } from './template/navbar/navbar.component';
import { TemplateComponent } from './template/template.component';
import { SharedModule } from '../shared/shared.module';
import { CoreRoutingModule } from './core-routing.module';
import { HomeComponent } from './home/home.component';
import { OverviewComponent } from './overview/overview.component';
import { NgModule } from '@angular/core';
import { HeadersInterceptor } from './headers.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
  imports: [
    SharedModule,
    CoreRoutingModule
  ],
  exports: [
    TemplateComponent
  ],
  declarations: [
    TemplateComponent,
    NavbarComponent,
    OverviewComponent,
    HomeComponent
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HeadersInterceptor,
      multi: true,
    },
    ToastService,
    PrivateGuard,
    AdminGuard
  ]
})
export class CoreModule {
}
