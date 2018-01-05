import { MessageType } from './message-type';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-message',
  templateUrl: 'message.component.html',
  styleUrls: ['message.component.scss']
})

export class MessageComponent implements OnInit {

  @Input()
  message = '';

  @Input()
  details = '';

  @Input()
  type = MessageType.DEFAULT;

  constructor() { }

  ngOnInit() { }

  getColor(): string {
    switch (this.type) {
      case MessageType.DEFAULT:
        return '';
      case MessageType.SUCCESS:
        return 'green';
      case MessageType.INFO:
        return 'blue';
      case MessageType.WARNING:
        return 'orange';
      case MessageType.ERROR:
        return 'red';
    }
  }

  hasIcon(): boolean {
    return this.type !== MessageType.DEFAULT;
  }

  getIcon(): string {
    switch (this.type) {
      case MessageType.SUCCESS:
        return 'checkmark';
      case MessageType.INFO:
        return 'info';
      case MessageType.WARNING:
        return 'warning';
      case MessageType.ERROR:
        return 'warning';
    }
  }
}
