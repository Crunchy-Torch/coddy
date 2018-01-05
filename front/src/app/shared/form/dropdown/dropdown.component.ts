import { Component, Input } from '@angular/core';
import { BasicFieldComponent } from '../basic-field.component';

@Component({
  /*tslint:disable:component-selector*/
  selector: '[app-dropdown]',
  /*tslint:enable*/
  templateUrl: './dropdown.component.html'
})
export class DropdownComponent extends BasicFieldComponent {

  @Input()
  items: string[];

  constructor() {
    super();
  }
}
