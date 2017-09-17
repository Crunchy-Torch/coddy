export interface Collection<T> {
  push(value: T): void;
  pop(): T;
  peek(): T;
  isEmpty(): boolean;
}