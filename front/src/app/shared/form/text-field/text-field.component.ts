import { Component } from '@angular/core';
import { BasicField } from '../basic-field.component';

@Component({
  /*tslint:disable:component-selector*/
  selector: '[app-text-field]',
  /*tslint:enable*/
  templateUrl: './text-field.component.html',
  styleUrls: ['./text-field.component.scss']
})
export class TextFieldComponent extends BasicField {

  constructor() {
    super();
  }
}
