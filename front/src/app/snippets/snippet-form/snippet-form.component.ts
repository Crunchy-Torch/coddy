import { LanguageService } from '../shared/language.service';
import { ToastService } from '../../core/template/toast.service';
import { Router } from '@angular/router';
import { Toast } from '../../shared/toast/toast';
import { Error } from '../../shared/error/error';
import { Snippet } from '../shared/snippet';
import { SnippetService } from '../shared/snippet.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, Input, OnInit } from '@angular/core';

declare var jQuery: any;

@Component({
  selector: 'app-snippet-form',
  templateUrl: './snippet-form.component.html',
  styleUrls: [ './snippet-form.component.scss' ]
})
export class SnippetFormComponent implements OnInit {

  @Input() snippet: Snippet;

  error: Error;
  snippetForm: FormGroup;
  languages: string[];
  isLoading = false;

  validationMessages = {
    'title': [
      'Must be at least 4 characters long.',
      'Must be at most 140 characters long.'
    ],
    'description': [
      'Must be at least 10 characters long.',
      'Must be at most 400 characters long.'
    ],
    'keywords': [
      'Provide a coma separated list of keywords'
    ],
    'content': [
      'Must be at least 10 characters long.',
      'Must be at most 500 characters long.'
    ],
  };

  constructor(private formBuilder: FormBuilder, private snippetService: SnippetService,
              private languageService: LanguageService, private toastService: ToastService,
              private router: Router) {
    this.createForm();
  }

  ngOnInit() {
    this.languageService.getLanguages().subscribe(
      res => {
        this.languages = res;
        jQuery('.ui.dropdown').dropdown({placeholder: 'Select a language'});
      }
    );
  }

  createForm() {
    this.snippetForm = this.formBuilder.group({
      title: [ '', [ Validators.required, Validators.minLength(5), Validators.maxLength(140) ] ],
      description: [ '', [ Validators.required, Validators.minLength(10), Validators.maxLength(400) ] ],
      language: this.formBuilder.group({
        name: [ '', Validators.required ],
        version: '',
      }),
      keywords: [ '', Validators.required ],
      content: [ '', [ Validators.required, Validators.minLength(10), Validators.maxLength(500) ] ]
    });
  }

  onSubmit() {
    if (this.snippetForm.valid) {
      this.isLoading = true;
      this.snippet = this.buildSnippet();
      this.snippetService.createSnippet(this.snippet).finally(() => {
        this.isLoading = false;
      }).subscribe(
        res => {
          this.pushToast();
          this.router.navigate([ '/overview' ]);
        },
        error => this.error = error
      );
    }
  }

  buildSnippet(): Snippet {
    const formModel = this.snippetForm.value;
    formModel.keywords = formModel.keywords.split(',');
    return Snippet.toObject(formModel);
  }

  pushToast() {
    this.toastService.pushToast(new Toast('green', 'Snippet created!', 'Your snippet has been successfully pushed'));
  }
}
