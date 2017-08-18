import { SharedModule } from './shared/shared.module';
import { SnippetsModule } from './snippets/snippets.module';
import { CoreModule } from './core/core.module';
import { AuthModule } from './auth/auth.module';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
    SharedModule,
    AuthModule,
    SnippetsModule
  ],
  declarations: [
    AppComponent,
    AdminComponent
  ],
  providers: [
    UserService,
    AdminService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
