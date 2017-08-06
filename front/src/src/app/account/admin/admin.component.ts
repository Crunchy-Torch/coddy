import { Component, OnInit } from '@angular/core';
import { User } from '../user/user';
import { AdminService } from './admin.service';
import { UserService } from '../user/user.service';

@Component({
  templateUrl: './admin.component.html'
})
export class AdminComponent implements OnInit {

  isLoading: boolean;
  isDeleteLoading: boolean;
  users: User[];
  error: Error;

  constructor(private adminService: AdminService, private userService: UserService) {

  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers() {
    this.isLoading = true;
    this.isDeleteLoading = false;
    this.users = null;
    this.error = null;
    this.adminService.getUsers().finally(
      () => this.isLoading = false
    ).subscribe(
      users => this.users = users,
      error => this.error = error
    )
  };

  deleteUser(login: string) {
    this.isDeleteLoading = true;
    this.error = null;
    this.userService.deleteUser(login).finally(
      () => {
        this.isDeleteLoading = false;
        this.users = this.users.filter(user => login != user.login);
      }
    ).subscribe(
      error => this.error = error
    )
  }

}