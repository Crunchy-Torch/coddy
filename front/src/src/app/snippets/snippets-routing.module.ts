import { PrivateComponent } from '../core/template/private/private.component';
import { SnippetComponent } from './snippet/snippet.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const PRIVATE_ROUTES: Routes = [
  { path: 'snippet/:id', component: SnippetComponent }
];

const SNIPPETS_ROUTES: Routes = [
  { path: '', component: PrivateComponent, children: PRIVATE_ROUTES }
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
