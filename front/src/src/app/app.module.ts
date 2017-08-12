import { CoreModule } from './core/core.module';
import { AuthModule } from './auth/auth.module';
import { FormsModule } from '@angular/forms';
import { SnippetService } from './snippet/snippet.service';
import { PrivateGuard } from './../template/private/private.guard';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { PrivateComponent } from '../template/private/private.component';
import { PublicComponent } from './../template/public/public.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { SnippetComponent } from './snippet/snippet.component';
import { AdminComponent } from './account/admin/admin.component';
import { UserService } from './account/user/user.service';
import { AdminService } from './account/admin/admin.service';

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    FormsModule,
    CoreModule,
    AuthModule
  ],
  declarations: [
    AppComponent,
    PublicComponent,
    PrivateComponent,
    HomeComponent,
    DashboardComponent,
    SnippetComponent,
    AdminComponent
  ],
  providers: [
    SnippetService,
    UserService,
    AdminService,
    PrivateGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
