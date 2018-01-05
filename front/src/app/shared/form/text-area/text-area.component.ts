import { Component, Input } from '@angular/core';
import { BasicField } from '../basic-field.component';

declare var jQuery: any;

@Component({
  /*tslint:disable:component-selector*/
  selector: '[app-text-area]',
  /*tslint:enable*/
  templateUrl: './text-area.component.html',
  styleUrls: [ './text-area.component.scss' ]
})
export class TextAreaComponent extends BasicField {

  @Input() rows = 3;

  constructor() {
    super();
  }
}
