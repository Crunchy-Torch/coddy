import { LanguageService } from '../shared/language.service';
import { ToastService } from '../../core/toast.service';
import { Router } from '@angular/router';
import { Toast } from '../../shared/toast/toast';
import { Error } from '../../shared/error/error';
import { Snippet } from '../shared/snippet';
import { SnippetService } from '../shared/snippet.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-snippet-form',
  templateUrl: './snippet-form.component.html',
  styleUrls: ['./snippet-form.component.scss']
})
export class SnippetFormComponent implements OnInit {

  @Input() snippet: Snippet;

  error: Error;
  generalInformationForm: FormGroup;
  codeForm: FormGroup;
  languages: Observable<string[]>;
  selectedIndex = 0;
  isLoading = false;

  errorMessages = {
    'title': 'Must be between 5 and 140 characters',
    'description': 'Must be between 10 and 400 characters',
    'keywords': 'You must enter a value'
  };

  hintMessages = {
    'keywords': 'Provide a coma separated list of keywords'
  };

  constructor(private formBuilder: FormBuilder, private snippetService: SnippetService,
              private languageService: LanguageService, private toastService: ToastService,
              private router: Router) {
    this.createForm();
  }

  ngOnInit() {
    this.languages = this.languageService.getLanguages();
  }

  createForm() {
    this.generalInformationForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(140)]],
      description: ['',
        [Validators.required, Validators.minLength(10), Validators.maxLength(400)]],
      keywords: ['', Validators.required]
    });
    this.codeForm = this.formBuilder.group({
      language: this.formBuilder.group({
        name: ['', Validators.required],
        version: '',
      }),
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
    const files = this.codeForm.get('files') as FormArray;
    files.push(this.createFile());
    this.selectedIndex = files.length - 1;
  }

  updatePageContent(control: any, value: string) {
    // control.value = value does not seem to work. We need to explicitly call the setter
    // and therefore define a custom event
    control.setValue(value);
  }

  submit() {
    if (this.generalInformationForm.valid && this.codeForm.valid) {
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
    const generalInformation = this.generalInformationForm.value;
    const codeInformation = this.codeForm.value;
    if (!(generalInformation.keywords instanceof Array)) {
      generalInformation.keywords = generalInformation.keywords.split(',');
    }
    return Snippet.toObject({...generalInformation, ...codeInformation});
  }

  pushToast() {
    this.toastService.pushToast(new Toast('green', 'Snippet created!', 'Your snippet has been successfully pushed'));
  }

  get snippetFormData() {
    // Cast to FormArray required to prevent "unknown property" error while building
    // https://github.com/angular/angular-cli/issues/6099
    return <FormArray>this.codeForm.get('files');
  }
}
