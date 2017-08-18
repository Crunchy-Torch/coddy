import { SharedModule } from '../shared/shared.module';
import { SnippetsRoutingModule } from './snippets-routing.module';
import { SnippetService } from './snippet/snippet.service';
import { SnippetComponent } from './snippet/snippet.component';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [
    SharedModule,
    SnippetsRoutingModule
  ],
  exports: [],
  declarations: [SnippetComponent],
  providers: [SnippetService],
})
export class SnippetsModule { }
