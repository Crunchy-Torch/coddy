import { AbstractControl } from '@angular/forms';
import { AfterViewInit, Component, Input, OnInit } from '@angular/core';

declare var jQuery: any;

@Component({
    selector: '[app-field]',
})
export class BasicField implements OnInit, AfterViewInit {

    @Input() control: AbstractControl;

    @Input() name: string;

    @Input() placeholder: string;

    @Input() help: string[];

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
