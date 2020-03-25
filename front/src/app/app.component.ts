import { Component, ViewChild } from '@angular/core';
import { TokenService } from './auth/token.service';
import { ToastService } from './core/toast.service';
import { Token } from './auth/token';
import { ToastComponent } from './shared/toast/toast.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent {
  @ViewChild(ToastComponent, { static: true }) toasts: ToastComponent;

  constructor(private tokenService: TokenService, private toastService: ToastService,
              private router: Router) {
  }

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
