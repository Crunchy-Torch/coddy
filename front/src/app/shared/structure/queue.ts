import { Collection } from './collection';

export class Queue<T> implements Collection<T>, Iterable<T> {

  front: Node<T>;
  rear: Node<T>;

  constructor() {
    this.front = null;
    this.rear = null;
  }

  [Symbol.iterator]() {
    let index: Node<T> = this.front;
    return {
      next: () => {
        let result;
        if (index !== null) {
          result = {
            done: false,
            value: index.value
          };
          index = index.next;
        } else {
          result = {
            done: true
          };
        }
        return result;
      }
    };
  }

  push(value: T) {
    const pushedNode: Node<T> = new Node(value);

    if (this.rear == null) {
      this.front = pushedNode;
      this.rear = pushedNode;
    } else {
      this.rear.next = pushedNode;
      this.rear = pushedNode;
    }
  }

  pop(): T {
    const poppedValue = this.front.value;
    this.front = this.front.next;
    if (!this.front) {
      this.rear = null;
    }
    return poppedValue;
  }

  peek(): T {
    if (this.front) {
      return this.front.value;
    } else {
      return null;
    }
  }

  isEmpty() {
    return this.rear === null;
  }
}

class Node<T> {
  value: T;
  next: Node<T>;
  constructor(value: T) {
    this.value = value;
    this.next = null;
  }
}
