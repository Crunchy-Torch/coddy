import { any } from 'codelyzer/util/function';
import { AbstractControl, Validators } from '@angular/forms';
import { AfterViewInit, Component, Input, OnInit } from '@angular/core';

declare var jQuery: any;

@Component({
  selector: '[app-field]',
  templateUrl: './field.component.html',
  styleUrls: ['./field.component.scss']
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

  constructor() { }

  ngOnInit() {
    this.improvedName = this.isMandatory() ? this.name + ' *' : this.name;
  }

  isMandatory(): boolean {
    let validator: any = this.control.validator && this.control.validator(this.control);
    return validator && validator.required;
  }

  ngAfterViewInit() {
    jQuery('#' + this.name).popup({ popup: '#' + this.name + '-popup', position: 'right center' });
  }
}