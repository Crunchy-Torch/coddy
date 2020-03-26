import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'languageFilter'
})
export class LanguageFilterPipe implements PipeTransform {

  public transform(languages: string[], value: string): string[] {

    if (languages == null || languages.length === 0) {
      return [];
    }

    const filterValue = value.toLowerCase();

    return languages.filter(language => language.toLowerCase().includes(filterValue));
  }
}
