
export class Snippet {
    title: string;
    description: string;
    language: Language;
    keywords: string[];
    content: string;
    associatedLinks: Link[];
    rate: number;
    author: string;
    created: string;
    lastModified: string;
}

export class Language {
    name: string;
    version: string;
}

export class Link {
    type: LinkType;
    value: string;
    description: string;
}

export enum LinkType {
    DOCUMENTATION, STACK_OVERFLOW, GITHUB, OTHER
}