import { SnippetService } from '../../snippets/snippet/snippet.service';
import { Snippet } from '../../snippets/snippet';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  isLoading: boolean;
  snippets: Snippet[];
  error: Error;

  constructor(private snippetService: SnippetService) {
  }

  ngOnInit() {
    this.getSnippets();
  }

  getSnippets() {
    this.isLoading = true;
    this.snippets = null;
    this.error = null;
    this.snippetService.getSnippets().finally(
      () => this.isLoading = false
    ).subscribe(
      snippets => this.snippets = snippets,
      error => this.error = error
    );
  }
}
