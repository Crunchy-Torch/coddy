
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

  static toObject(raw: any): Snippet {
    let result: Snippet = new Snippet();
    result.title = raw.title;
    result.description = raw.description;
    result.language = Language.toObject(raw.language);
    result.keywords = raw.keywords;
    result.content = raw.content;
    result.associatedLinks = [];
    if (raw.associatedLinks instanceof Array) {
      raw.associatedLinks.forEach(rawLink => {
        result.associatedLinks.push(Link.toObject(rawLink));
      });
    }
    result.rate = raw.rate;
    result.author = raw.author;
    result.created = raw.created;
    result.lastModified = raw.lastModified;
    return result;
  }
}

export class Language {
  name: string;
  version: string;

  static toObject(raw: any): Language {
    let result: Language = new Language();

    if (typeof raw !== 'undefined') {
      result.name = raw.name;
      result.version = raw.version;
    }
    return result;
  }
}

export class Link {
  type: LinkType;
  value: string;
  description: string;

  static toObject(raw: any): Link {
    let result: Link = new Link();

    if (typeof raw !== 'undefined') {
      result.type = LinkType[raw.type as string];
      result.value = raw.value;
      result.description = raw.description;
    }
    return result;
  }
}

export enum LinkType {
  DOCUMENTATION, STACK_OVERFLOW, GITHUB, OTHER
}