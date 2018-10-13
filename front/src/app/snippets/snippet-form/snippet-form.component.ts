import { LanguageService } from '../shared/language.service';
import { ToastService } from '../../core/template/toast.service';
import { Router } from '@angular/router';
import { Toast } from '../../shared/toast/toast';
import { Error } from '../../shared/error/error';
import { Snippet } from '../shared/snippet';
import { SnippetService } from '../shared/snippet.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, Input, OnInit } from '@angular/core';

declare var jQuery: any;

@Component({
  selector: 'app-snippet-form',
  templateUrl: './snippet-form.component.html',
  styleUrls: ['./snippet-form.component.scss']
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
    ]
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
      title: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(140)]],
      description: ['',
        [Validators.required, Validators.minLength(10), Validators.maxLength(400)]],
      language: this.formBuilder.group({
        name: ['', Validators.required],
        version: '',
      }),
      keywords: ['', Validators.required],
      files: this.formBuilder.array([this.createFile()])
    });
  }

  createFile() {
    return this.formBuilder.group({
      filename: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  addFile() {
    const files = this.snippetForm.get('files') as FormArray;
    files.push(this.createFile());
    setTimeout(() => jQuery('.menu .item').tab('change tab', 'tab' + (files.length - 1)), 50);
  }

  updatePageContent(control: any, value: string) {
    // control.value = value does not seem to work. We need to explicitly call the setter
    // and therefore define a custom event
    control.setValue(value);
  }

  onSubmit() {
    if (this.snippetForm.valid) {
      this.isLoading = true;
      this.snippet = this.buildSnippet();
      this.snippetService.createSnippet(this.snippet).subscribe(
        () => {
          this.pushToast();
          this.router.navigate(['/overview']);
        },
        error => this.error = error,
        () => this.isLoading = false
      );
      this.isLoading = false;
    }
  }

  buildSnippet(): Snippet {
    const formModel = this.snippetForm.value;
    if (!(formModel.keywords instanceof Array)) {
      formModel.keywords = formModel.keywords.split(',');
    }
    return Snippet.toObject(formModel);
  }

  pushToast() {
    this.toastService.pushToast(new Toast('green', 'Snippet created!', 'Your snippet has been successfully pushed'));
  }
}
