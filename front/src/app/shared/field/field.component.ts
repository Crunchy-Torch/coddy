import { AbstractControl } from '@angular/forms';
import { AfterViewInit, Component, Input, OnInit } from '@angular/core';

declare var jQuery: any;

@Component({
  /*tslint:disable:component-selector*/
  selector: '[app-field]',
  /*tslint:enable*/
  templateUrl: './field.component.html',
  styleUrls: [ './field.component.scss' ]
})
export class FieldComponent implements OnInit, AfterViewInit {

  @Input() control: AbstractControl;

  @Input() name: string;

  @Input() placeholder: string;

  @Input() help: string[];

  @Input() type = 'text';

  @Input() isTextArea = false;

  @Input() rows = 3;

  improvedName: string;

  constructor() {
  }

  ngOnInit() {
    this.improvedName = this.isMandatory() ? this.name + ' *' : this.name;
  }

  isMandatory(): boolean {
    const validator: any = this.control.validator && this.control.validator(this.control);
    return validator && validator.required;
  }

  ngAfterViewInit() {
    jQuery('#' + this.name).popup({popup: '#' + this.name + '-popup', position: 'right center'});
  }
}
