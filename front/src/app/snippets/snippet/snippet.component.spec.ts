import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SnippetComponent } from './snippet.component';
import { SharedModule } from "../../shared/shared.module";
import { HttpModule } from "@angular/http";
import { SnippetService } from "../shared/snippet.service";
import { RouterTestingModule } from "@angular/router/testing";
import { AuthModule } from "../../auth/auth.module";

describe('SnippetComponent', () => {
  let component: SnippetComponent;
  let fixture: ComponentFixture<SnippetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SnippetComponent ],
      imports: [ SharedModule, AuthModule, HttpModule, RouterTestingModule.withRoutes([]) ],
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
