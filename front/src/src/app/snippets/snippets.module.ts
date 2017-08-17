import { SharedModule } from '../shared/shared.module';
import { SnippetsRoutingModule } from './snippets-routing.module';
import { CommonModule } from '@angular/common';
import { SnippetService } from './snippet/snippet.service';
import { SnippetComponent } from './snippet/snippet.component';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    SnippetsRoutingModule
  ],
  exports: [],
  declarations: [SnippetComponent],
  providers: [SnippetService],
})
export class SnippetsModule { }
