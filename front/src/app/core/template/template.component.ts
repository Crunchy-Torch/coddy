import { ToastService } from './toast.service';
import { Token } from '../../auth/token';
import { Router } from '@angular/router';
import { TokenService } from '../../auth/token.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ToastComponent } from '../../shared/toast/toast.component';

@Component({
  selector: 'app-template',
  templateUrl: 'template.component.html'
})

export class TemplateComponent implements OnInit {

  @ViewChild(ToastComponent) toasts: ToastComponent;

  constructor(private tokenService: TokenService, private toastService: ToastService,
    private router: Router) { }

  ngOnInit() {
    this.toastService.get().subscribe(toast => {
      this.toasts.addToast(toast);
    });
  }

  getToken(): Token {
    return this.tokenService.getToken();
  }

  isLogged(): boolean {
    return this.tokenService.hasValidToken();
  }

  isHomepage(): boolean {
    return this.router.url === '/';
  }

  logout() {
    this.tokenService.clearToken();
    this.router.navigate(['/']);
  }

  isLoginPage(): boolean {
    return this.router.url.split(';')[0] === '/login';
  }
}