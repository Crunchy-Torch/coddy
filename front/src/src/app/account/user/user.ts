export class User {
  login: String;
  password: String;
  firstName: String;
  lastName: String;
  email: String;

  constructor(raw: any) {
    this.login = raw.login;
    this.password = raw.password;
    this.firstName = raw.firstName;
    this.lastName = raw.lastName;
    this.email = raw.email;
  }
}