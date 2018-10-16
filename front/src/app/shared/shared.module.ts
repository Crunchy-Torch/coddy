import { MessageComponent } from './message/message.component';
import { TitleComponent } from './title/title.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BaseService } from './base.service';
import { ErrorComponent } from './error/error.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { TextFieldComponent } from './form/text-field/text-field.component';
import { ToastComponent } from './toast/toast.component';
import { TextAreaComponent } from './form/text-area/text-area.component';
import { DropdownComponent } from './form/dropdown/dropdown.component';
import { BasicFieldComponent } from './form/basic-field.component';
import { SharedMaterialModule } from './shared-material.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedMaterialModule,
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedMaterialModule,
    MessageComponent,
    ErrorComponent,
    TitleComponent,
    TextFieldComponent,
    ToastComponent,
    TextAreaComponent,
    DropdownComponent
  ],
  declarations: [
    MessageComponent,
    ErrorComponent,
    TitleComponent,
    TextFieldComponent,
    ToastComponent,
    TextAreaComponent,
    DropdownComponent,
    BasicFieldComponent
  ],
  providers: [BaseService],
})
export class SharedModule {
}
