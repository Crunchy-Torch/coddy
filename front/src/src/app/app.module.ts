import { TokenService } from './shared/token.service';
import { FormsModule } from '@angular/forms';
import { LoginService } from './login/login.service';
import { ErrorComponent } from './shared/error/error.component';
import { SnippetService } from './snippet/snippet.service';
import { PrivateGuard } from './../template/private/private.guard';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { PrivateComponent } from '../template/private/private.component';
import { PublicComponent } from './../template/public/public.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppService } from './app.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { SnippetComponent } from './snippet/snippet.component';
import { AdminComponent } from './account/admin/admin.component';
import { UserService } from './account/user/user.service';
import { AdminService } from './account/admin/admin.service';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    PublicComponent,
    PrivateComponent,
    ErrorComponent,
    HomeComponent,
    DashboardComponent,
    SnippetComponent,
    AdminComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    AppService,
    SnippetService,
    UserService,
    AdminService,
    LoginService,
    TokenService,
    PrivateGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
