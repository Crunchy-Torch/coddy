import { SnippetComponent } from './snippet/snippet.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const snippetsRoutes: Routes = [
  { path: 'snippet/:id', component: SnippetComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(snippetsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class SnippetsRoutingModule { }
