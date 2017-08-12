import { BaseService } from './base.service';
import { ErrorComponent } from './error/error.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    CommonModule
  ],
  exports: [
    ErrorComponent
  ],
  declarations: [
    ErrorComponent
  ],
  providers: [
    BaseService
  ]
})
export class CoreModule { }
