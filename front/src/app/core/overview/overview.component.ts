import { SnippetService } from '../../snippets/shared/snippet.service';
import { Snippet } from '../../snippets/shared/snippet';
import { Component, OnInit } from '@angular/core';
import { Page } from '../../shared/structure/page';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: [ './overview.component.scss' ]
})
export class OverviewComponent implements OnInit {

  isLoading: boolean;
  isSearch: boolean;
  pageSnippets: Page<Snippet>;
  error: Error;

  constructor(private snippetService: SnippetService) {
  }

  ngOnInit() {
    this.getSnippets();
  }

  getSnippets(word?: string, from: number = 0, size: number = 10) {
    this.isLoading = true;
    this.pageSnippets = null;
    this.error = null;
    this.snippetService.getSnippets(word, from, size).finally(
      () => {
        this.isLoading = false;
        this.isSearch = word && word !== '';
      }
    ).subscribe(
      pageSnippets => this.pageSnippets = pageSnippets,
      error => this.error = error
    );
  }
}
