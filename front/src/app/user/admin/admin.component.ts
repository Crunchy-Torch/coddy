import { Component, OnInit } from '@angular/core';
import { User } from '../user/user';
import { AdminService } from './admin.service';
import { UserService } from '../user/user.service';

@Component({
  templateUrl: './admin.component.html'
})
export class AdminComponent implements OnInit {

  isFirstLoading: boolean;
  isDeleteLoading: boolean;
  isUserLoading: boolean;
  users: User[];
  error: Error;
  nbUser: number;
  paginationSize: number;
  paginationArray: number [];
  pageNumber: number;

  constructor(private adminService: AdminService, private userService: UserService) {
    this.nbUser = -1;
    this.paginationSize = 10;
    this.pageNumber = 1;
  }

  ngOnInit(): void {
    this.reinit();
  }

  reinit(): void {
    this.getUsersFirstLoading();
    this.countUser();
  }

  getUsersFirstLoading() {
    this.isFirstLoading = true;
    this.isUserLoading = true;
    this.isDeleteLoading = false;
    this.users = null;
    this.error = null;
    this.adminService.getUsers().finally(
      () => {
        this.isFirstLoading = false;
        this.isUserLoading = false;
      }
    ).subscribe(
      users => this.users = users,
      error => this.error = error
    )
  };

  getUsers(pageNumber?: number) {
    if (pageNumber) {
      this.pageNumber = pageNumber;
    }
    this.isUserLoading = true;
    this.error = null;

    this.adminService.getUsers(this.pageNumber - 1, this.paginationSize)
      .finally(
        () => this.isUserLoading = false
      )
      .subscribe(
        users => this.users = users,
        error => this.error = error
      )
  }

  previousPage() {
    if (this.pageNumber - 1 < 1) {
      return;
    }

    this.pageNumber--;
    this.getUsers();
  }

  nextPage() {
    if (this.pageNumber + 1 > this.paginationArray[ this.paginationArray.length - 1 ]) {
      return;
    }

    this.pageNumber++;
    this.getUsers();
  }

  deleteUser(login: string) {
    this.isDeleteLoading = true;
    this.error = null;
    this.userService.deleteUser(login).finally(
      () => {
        this.isDeleteLoading = false;
        this.users = this.users.filter(user => login != user.login);
        this.countUser();
      }
    ).subscribe(
      error => this.error = error
    )
  }

  countUser() {
    this.adminService.count().finally(
      () => {
        let x = 1;
        let y = 1;
        this.paginationArray = [];
        while (x <= this.nbUser) {
          this.paginationArray.push(y);
          y++;
          x = this.paginationSize + x;
        }
      }
    ).subscribe(
      nbUser => this.nbUser = nbUser
    )
  }

}