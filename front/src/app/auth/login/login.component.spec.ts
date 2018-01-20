import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { SharedModule } from '../../shared/shared.module';
import { LoginService } from './login.service';
import { TokenService } from '../token.service';
import { RouterTestingModule } from '@angular/router/testing';
import {HttpClientModule} from "@angular/common/http";

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [ SharedModule, HttpClientModule, RouterTestingModule.withRoutes([]) ],
      providers: [ LoginService, TokenService ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
