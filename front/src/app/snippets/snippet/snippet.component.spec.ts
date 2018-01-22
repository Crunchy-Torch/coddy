import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SnippetComponent } from './snippet.component';
import { SharedModule } from '../../shared/shared.module';
import { SnippetService } from '../shared/snippet.service';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthModule } from '../../auth/auth.module';
import {HttpClientModule} from '@angular/common/http';

describe('SnippetComponent', () => {
  let component: SnippetComponent;
  let fixture: ComponentFixture<SnippetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SnippetComponent ],
      imports: [ SharedModule, AuthModule, HttpClientModule, RouterTestingModule.withRoutes([]) ],
      providers: [ SnippetService ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SnippetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
