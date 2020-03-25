import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';

declare var ace: any;

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html'
})
export class EditorComponent implements OnInit {

  @Input() language: string;
  @Input() content: string;
  @Input() readOnly = true;
  aceTheme = 'textmate';

  @ViewChild('editor', { static: true }) editor;
  @Output() contentChange: EventEmitter<any> = new EventEmitter();

  ngOnInit() {
    // ng2-ace-editor does not provide (yet) an elegant way to do this
    ace.config.set('basePath', '/assets/ace-builds/src-min-noconflict');

    this.editor.setTheme(this.aceTheme);
    this.editor.setOptions(
      {
        enableBasicAutocompletion: true,
        highlightActiveLine: !this.readOnly,
        highlightGutterLine: !this.readOnly,
        fontSize: '1.1em'
      }
    );

    if (this.readOnly) {
      this.editor.getEditor().renderer.$cursorLayer.element.style.display = 'none';
    }
  }

  onContentChange(content) {
    this.contentChange.emit(content);
  }
}
