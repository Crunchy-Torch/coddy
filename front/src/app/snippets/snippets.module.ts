import { LanguageService } from './shared/language.service';
import { SharedModule } from '../shared/shared.module';
import { SnippetsRoutingModule } from './snippets-routing.module';
import { SnippetService } from './shared/snippet.service';
import { SnippetComponent } from './snippet/snippet.component';
import { NgModule } from '@angular/core';
import { SnippetFormComponent } from './snippet-form/snippet-form.component';
import { AceEditorModule } from 'ng2-ace-editor';

@NgModule({
  imports: [
    SharedModule,
    SnippetsRoutingModule,
    AceEditorModule,
  ],
  exports: [],
  declarations: [SnippetComponent, SnippetFormComponent],
  providers: [SnippetService, LanguageService],
})
export class SnippetsModule { }
