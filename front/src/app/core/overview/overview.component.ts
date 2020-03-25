import { SnippetService } from '../../snippets/shared/snippet.service';
import { Snippet } from '../../snippets/shared/snippet';
import { Component, OnInit } from '@angular/core';
import { Page } from '../../shared/structure/page';
import { PageEvent } from '@angular/material/paginator';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  isLoading: boolean;
  query: string;
  pastQuery: string;
  displayedColumns: string[] = ['name', 'description', 'language', 'author'];
  pageSnippets: Page<Snippet>;
  error: Error;

  timeout: any;

  pageSize = 5;
  pageIndex = 0;

  constructor(private snippetService: SnippetService) {
  }

  ngOnInit() {
    this.getSnippets();
  }

  delaySearchSnippets(query: string) {
    clearTimeout(this.timeout);
    this.timeout = setTimeout(() => {
      this.query = query;
      this.searchSnippets();
    }, 500);
  }

  // when the user presses on enter or quit the input
  enterSearchSnippets(query: string) {
    clearTimeout(this.timeout);
    this.query = query;
    this.searchSnippets();
  }

  searchSnippets() {
    if (this.query) {
      this.query = this.query.trim();
    }
    this.pageIndex = 0;
    this.getSnippets(this.query);
    this.pastQuery = this.query;
  }

  reloadSnippets() {
    this.pageIndex = 0;
    this.query = null;
    this.getSnippets();
  }

  getSnippets(word?: string, from: number = 0, size: number = this.pageSize) {
    this.isLoading = true;
    this.pageSnippets = null;
    this.error = null;
    this.snippetService.getSnippets(word, from, size).pipe(
      finalize(() => this.isLoading = false)
    ).subscribe(
      pageSnippets => this.pageSnippets = pageSnippets,
      error => this.error = error
    );
  }

  paginatorChange(pageEvent: PageEvent) {
    this.pageSize = pageEvent.pageSize;
    this.pageIndex = pageEvent.pageIndex;
    this.getSnippets(this.query, this.pageIndex, this.pageSize);
  }
}
