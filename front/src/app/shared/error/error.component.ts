import { Error } from './error';
import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-error',
    templateUrl: 'error.component.html',
    styleUrls: ['error.component.scss']
})

export class ErrorComponent {

    @Input() error: Error;

    constructor() { }

    hasStatus(): boolean {
        return this.error.status !== 0;
    }
}