export class Toast {

  color: string;
  title: string;
  text: string;

  constructor(color?: string, title?: string, text?: string) {
    this.color = color;
    this.title = title;
    this.text = text;
  }
}