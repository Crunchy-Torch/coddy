import {Component, Input} from '@angular/core';
import {BasicField} from "../basic-field.component";

@Component({
  selector: '[app-dropdown]',
  templateUrl: './dropdown.component.html'
})
export class DropdownComponent extends BasicField {

  @Input()
  items: string[];

  constructor() {
    super();
  }
}
