import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'app';
  helloWorld = 'cannot reach API';

  constructor(private appService: AppService){
  }

  ngOnInit(){
    this.sayHello();    
  }

  sayHello() {
    console.log('say hello!');
    this.appService.sayHello().subscribe(res => this.helloWorld = res);
  }
}
