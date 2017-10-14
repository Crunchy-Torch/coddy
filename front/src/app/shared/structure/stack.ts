import { Collection } from './collection';

export class Stack<T> implements Collection<T> {
  top: any;

  constructor() {
    this.top = null;
  }

  push(value: T): void {
    this.top = {
      value: value,
      next: this.top
    };
  }

  pop(): T {
    let value: T = this.top.value;
    this.top = this.top.next;
    return value;
  }

  peek(): T {
    return this.top.value;
  }

  isEmpty(): boolean {
    return this.top === null;
  }
}