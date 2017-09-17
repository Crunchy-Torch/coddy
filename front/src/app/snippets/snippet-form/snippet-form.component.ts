import { Snippet } from '../shared/snippet';
import { SnippetService } from '../shared/snippet.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-snippet-form',
  templateUrl: './snippet-form.component.html',
  styleUrls: ['./snippet-form.component.scss']
})
export class SnippetFormComponent {

  @Input() snippet: Snippet;

  snippetForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private snippetService: SnippetService) {
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

  onSubmit() {
    console.log('submit!');
    this.snippet = this.buildSnippet();
    this.snippetService.createSnippet(this.snippet).subscribe(
      res => console.log(res),
      err => console.error(err)
    );
  }

  buildSnippet(): Snippet {
    const formModel = this.snippetForm.value;
    formModel.keywords = formModel.keywords.split(',');
    return Snippet.toObject(formModel);
  }
}
