import { Error } from '../../shared/error/error';
import { ActivatedRoute } from '@angular/router';
import { SnippetService } from '../shared/snippet.service';
import { LinkType, Snippet } from '../shared/snippet';

import { Component, OnInit } from '@angular/core';

declare var jQuery: any;

@Component({
  selector: 'app-snippet',
  templateUrl: './snippet.component.html',
  styleUrls: ['./snippet.component.scss']
})
export class SnippetComponent implements OnInit {

  isLoading: boolean;
  id: string;
  snippet: Snippet;
  error: Error;

  constructor(private snippetService: SnippetService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.isLoading = true;
    this.route.params.subscribe((params: { id: string }) => {
      // Get route parameters
      this.id = params.id;
      this.getSnippet();
    });
  }

  getSnippet() {
    this.isLoading = true;
    this.error = null;
    this.snippet = null;
    this.snippetService.getSnippet(this.id).finally(
      () => this.isLoading = false
    ).subscribe(
      snippet => {
        this.snippet = snippet;
        // Wait DOM is fully updated before calling tab method.
        setTimeout(() => jQuery('.menu .item').tab(), 500);
      },
      error => this.error = error
    );
  }

  linkTypeClass(linkType: LinkType): string {

    switch (linkType) {
      case LinkType.DOCUMENTATION:
        return 'book icon';
      case LinkType.STACK_OVERFLOW:
        return 'stack overflow icon';
      case LinkType.GITHUB:
        return 'github icon';
      default:
        return 'linkify icon';
    }
  }

  intToArray(int: number): Array<number> {
    return Array.from(Array(int), (x, i) => i);
  }
}
