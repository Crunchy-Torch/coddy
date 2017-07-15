import { Language, Link, LinkType, Snippet } from './snippet';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-snippet',
  templateUrl: './snippet.component.html',
  styleUrls: ['./snippet.component.css']
})
export class SnippetComponent implements OnInit {

  snippet: Snippet;

  constructor() {

    this.snippet = new Snippet();
    this.snippet.title = 'For loop';
    let language: Language = new Language();
    language.name = 'Java';
    language.version = '1.8';
    this.snippet.language = language;
    this.snippet.keywords = ['Java', 'Object', 'Loop', 'For'];
    this.snippet.content = 'for int i = 0\ntest';
    this.snippet.description = 'Basic Java loop';
    let link1: Link = new Link();
    link1.type = LinkType.DOCUMENTATION;
    link1.value = 'http://perdu.com';
    link1.description = 'Perdu sur Internet ?';
    let link2: Link = new Link();
    link2.type = LinkType.STACK_OVERFLOW;
    link2.value = 'http://stackoverflow.com';
    link2.description = 'Forum stack';
    this.snippet.associatedLinks = [link1, link2];
    this.snippet.rate = 3;
    this.snippet.author = 'David Levayer';
    this.snippet.created = new Date();
    this.snippet.lastModified = new Date();
  }

  ngOnInit() {
  }

  linkTypeClass(linkType: LinkType): string {

    switch(linkType) {
      case LinkType.DOCUMENTATION: return 'book icon';
      case LinkType.STACK_OVERFLOW: return 'stack overflow icon';
      case LinkType.GITHUB: return 'github icon';
      default: return 'linkify icon';
    }
  }

  intToArray(int: number): Array<number> {
    return Array.from(Array(int),(x,i)=>i);
  }
}
