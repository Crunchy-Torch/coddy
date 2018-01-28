import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: [ './signup.component.scss' ]
})
export class SignupComponent implements OnInit {

  email: string;
  login: string;
  firstname: string;
  lastname: string;
  password: string;
  confirmPassword: string;

  signupForm: FormGroup;
  error: Error;

  constructor() {
  }

  ngOnInit() {
    this.initializeFormGroup();
  }

  initializeFormGroup() {
    this.signupForm = new FormGroup({
      'loginValidator': new FormControl(this.login, [
        Validators.required
      ]),
      'emailValidator': new FormControl(this.email, [
        Validators.required,
        Validators.email
      ]),
      'passwordValidator': new FormControl(this.password, [
        Validators.required,
        Validators.minLength(8)
      ]),
      'confirmPasswordValidator': new FormControl(this.confirmPassword, [
        Validators.required,
        this.confirmPasswordEqualsValidator()
      ])
    })
  }

  get loginValidator() {
    return this.signupForm.get('loginValidator');
  }

  get emailValidator() {
    return this.signupForm.get('emailValidator');
  }

  get passwordValidator() {
    return this.signupForm.get('passwordValidator');
  }

  get confirmPasswordValidator() {
    return this.signupForm.get('confirmPasswordValidator');
  }

  confirmPasswordEqualsValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      const isEquals = this.password !== control.value;
      return isEquals ? {'confirmPasswordNotEquals': {value: control.value}} : null;
    };
  }

}
