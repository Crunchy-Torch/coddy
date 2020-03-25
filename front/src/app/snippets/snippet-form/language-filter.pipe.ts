import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'languageFilter'
})
export class LanguageFilterPipe implements PipeTransform {
  public transform(languages: string[], value: string) {

    const filterValue = value.toLowerCase();

    return languages.filter(language => language.toLowerCase().includes(filterValue));
  }
}