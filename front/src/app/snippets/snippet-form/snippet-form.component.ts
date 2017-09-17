import { ToastComponent } from '../../shared/toast/toast.component';
import { Toast } from '../../shared/toast/toast';
import { Error } from '../../shared/error/error';
import { Snippet } from '../shared/snippet';
import { SnippetService } from '../shared/snippet.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, Input, ViewChild } from '@angular/core';

@Component({
  selector: 'app-snippet-form',
  templateUrl: './snippet-form.component.html',
  styleUrls: ['./snippet-form.component.scss']
})
export class SnippetFormComponent {

  @Input() snippet: Snippet;
  @ViewChild(ToastComponent) toasts: ToastComponent;
  error: Error;
  snippetForm: FormGroup;
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

  constructor(private formBuilder: FormBuilder, private snippetService: SnippetService) {
    this.createForm();
  }

  createForm() {
    this.snippetForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(140)]],
      description: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(400)]],
      language: this.formBuilder.group({
        name: ['', Validators.required],
        version: '',
      }),
      keywords: ['', Validators.required],
      content: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(500)]]
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
          this.createForm();
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
    this.toasts.addToast(new Toast('green', 'Snippet created!', 'Your snippet has been successfully pushed'))
  }
}
