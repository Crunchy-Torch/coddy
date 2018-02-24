import { SnippetService } from '../../snippets/shared/snippet.service';
import { Snippet } from '../../snippets/shared/snippet';
import { Component, OnInit } from '@angular/core';
import { Page } from '../../shared/structure/page';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  isLoading: boolean;
  pageSnippets: Page<Snippet>;
  error: Error;

  constructor(private snippetService: SnippetService) {
  }

  ngOnInit() {
    this.getSnippets();
  }

  getSnippets() {
    this.isLoading = true;
    this.pageSnippets = null;
    this.error = null;
    this.snippetService.getSnippets().finally(
      () => this.isLoading = false
    ).subscribe(
      pageSnippets => this.pageSnippets = pageSnippets,
      error => this.error = error
    );
  }
}
