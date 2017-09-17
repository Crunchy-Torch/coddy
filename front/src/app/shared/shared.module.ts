import { TitleComponent } from './title/title.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BaseService } from './base.service';
import { ErrorComponent } from './error/error.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

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
    TitleComponent
  ],
  declarations: [
    ErrorComponent,
    TitleComponent
  ],
  providers: [BaseService],
})
export class SharedModule { }
