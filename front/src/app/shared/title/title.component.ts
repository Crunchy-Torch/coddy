import { Location } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-title',
  templateUrl: 'title.component.html',
  styleUrls: ['title.component.scss']
})

export class TitleComponent {

  @Input()
  text: string;

  @Input()
  back = false;

  constructor(private location: Location) { }

  goBack() {
    this.location.back();
  }
}
