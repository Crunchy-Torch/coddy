import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component } from '@angular/core';

@Component({
  selector: 'app-snippet-form',
  templateUrl: './snippet-form.component.html',
  styleUrls: ['./snippet-form.component.scss']
})
export class SnippetFormComponent {

  snippetForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm() {
    this.snippetForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      language: this.formBuilder.group({
        name: ['', Validators.required],
        version: '',
      }),
      keywords: ['', Validators.required],
      content: ['', Validators.required]
    });
  }
}
