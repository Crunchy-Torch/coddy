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
import { UserComponent } from './user/user.component';
import { UserService } from './user/user.service';

@NgModule({
  declarations: [
    AppComponent,
    PublicComponent,
    PrivateComponent,
    ErrorComponent,
    HomeComponent,
    DashboardComponent,
    SnippetComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [
    AppService,
    SnippetService,
    UserService,
    PrivateGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
