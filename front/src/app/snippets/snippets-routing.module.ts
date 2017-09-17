import { SnippetFormComponent } from './snippet-form/snippet-form.component';
import { SnippetComponent } from './snippet/snippet.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const SNIPPETS_ROUTES: Routes = [
  { path: 'snippet/create', component: SnippetFormComponent },
  { path: 'snippet/:id', component: SnippetComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(SNIPPETS_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class SnippetsRoutingModule { }
