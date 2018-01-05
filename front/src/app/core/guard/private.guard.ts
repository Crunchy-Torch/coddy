import { TokenService } from '../../auth/token.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class PrivateGuard implements CanActivate {
  constructor(private tokenService: TokenService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    const hasToken = this.tokenService.hasToken();

    if (!hasToken) {
      this.router.navigate(['login', { redirect: true }]);
      return false;
    }

    const hasNotExpired = this.tokenService.hasNotExpired();

    if (!hasNotExpired) {
      this.tokenService.clearToken();
      this.router.navigate(['login', { tokenHasExpired: true }]);
      return false;
    }

    return true;
  }
}
