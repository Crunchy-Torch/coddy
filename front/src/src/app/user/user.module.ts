import { AdminComponent } from './admin/admin.component';
import { AdminService } from './admin/admin.service';
import { UserService } from './user/user.service';
import { UserRoutingModule } from './user-routing.module';
import { SharedModule } from '../shared/shared.module';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [
    SharedModule,
    UserRoutingModule
  ],
  exports: [],
  declarations: [
    AdminComponent
  ],
  providers: [
    UserService,
    AdminService
  ],
})
export class UserModule { }
