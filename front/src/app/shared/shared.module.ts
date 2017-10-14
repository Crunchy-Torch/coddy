import { MessageComponent } from './message/message.component';
import { TitleComponent } from './title/title.component';
import { FormsModule } from '@angular/forms';
import { BaseService } from './base.service';
import { ErrorComponent } from './error/error.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    MessageComponent,
    ErrorComponent,
    TitleComponent
  ],
  declarations: [
    MessageComponent,
    ErrorComponent,
    TitleComponent
  ],
  providers: [BaseService],
})
export class SharedModule { }
