export enum LinkType {
  DOCUMENTATION, STACK_OVERFLOW, GITHUB, OTHER
}

export class Link {
  type: LinkType;
  value: string;
  description: string;

  static toObject(raw: any): Link {
    const result: Link = new Link();

    if (typeof raw !== 'undefined') {
      result.type = LinkType[raw.type as string];
      result.value = raw.value;
      result.description = raw.description;
    }
    return result;
  }
}

export class Language {
  name: string;
  version: string;

  static toObject(raw: any): Language {
    const result: Language = new Language();

    if (typeof raw !== 'undefined') {
      result.name = raw.name;
      result.version = raw.version;
    }
    return result;
  }
}

export class File {
  filename: string;
  language: Language;
  content: string;

  static toObject(raw: any): File {
    const result: File = new File();

    if (typeof raw !== 'undefined') {
      result.filename = raw.filename;
      result.language = Language.toObject(raw.language);
      result.content = raw.content;
    }
    return result;
  }
}

export class Snippet {
  title: string;
  description: string;
  language: Language;
  keywords: string[];
  files: File[];
  associatedLinks: Link[];
  rate: number;
  author: string;
  created: string;
  lastModified: string;

  static toObject(raw: any): Snippet {
    const result: Snippet = new Snippet();
    result.title = raw.title;
    result.description = raw.description;
    result.language = Language.toObject(raw.language);
    result.keywords = raw.keywords;
    result.files = [];
    if (raw.files instanceof Array) {
      raw.files.forEach(rawFile => {
        result.files.push(File.toObject(rawFile));
      });
    }
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
