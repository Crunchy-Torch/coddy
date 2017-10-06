import { Toast } from '../../shared/toast/toast';
import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class ToastService {

  private toastEmitter: EventEmitter<Toast> = new EventEmitter();

  constructor() { }

  get(): EventEmitter<Toast> {
    return this.toastEmitter;
  }

  pushToast(toast: Toast) {
    this.toastEmitter.emit(toast);
  }
}