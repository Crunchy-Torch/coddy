import { Component, Input } from '@angular/core';
import { BasicField } from '../basic-field.component';

@Component({
  /*tslint:disable:component-selector*/
  selector: '[app-dropdown]',
  /*tslint:enable*/
  templateUrl: './dropdown.component.html'
})
export class DropdownComponent extends BasicField {

  @Input()
  items: string[];

  constructor() {
    super();
  }
}
