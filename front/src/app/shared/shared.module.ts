import { TitleComponent } from './title/title.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BaseService } from './base.service';
import { ErrorComponent } from './error/error.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FieldComponent } from './field/field.component';
import { ToastComponent } from './toast/toast.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ErrorComponent,
    TitleComponent,
    FieldComponent,
    ToastComponent
  ],
  declarations: [
    ErrorComponent,
    TitleComponent,
    FieldComponent,
    ToastComponent
  ],
  providers: [BaseService],
})
export class SharedModule { }
