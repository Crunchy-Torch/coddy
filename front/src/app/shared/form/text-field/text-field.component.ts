import { Component } from '@angular/core';
import {BasicField} from "../basic-field.component";

@Component({
  selector: '[app-text-field]',
  templateUrl: './text-field.component.html',
  styleUrls: ['./text-field.component.scss']
})
export class TextFieldComponent extends BasicField {

  constructor() {
    super();
  }
}
