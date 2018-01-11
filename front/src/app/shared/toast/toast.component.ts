import { Queue } from '../structure/queue';
import { Toast } from './toast';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.scss']
})
export class ToastComponent {

  toasts: Queue<Toast> = new Queue<Toast>();

  @Input() position = 'bottom right';

  @Input() duration = 5000;

  constructor() { }

  addToast(toast: Toast) {
    this.toasts.push(toast);
    setTimeout(() => {
      this.toasts.pop();
    }, this.duration);
  }

  hide(toast: Toast) {
    // Toast will be automatically remove once time is up. Just hide it until then.
    toast.hidden = true;
  }
}
