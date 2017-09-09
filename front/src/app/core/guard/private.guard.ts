import { TokenService } from '../../auth/token.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class PrivateGuard implements CanActivate {
  constructor(private tokenService: TokenService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    let isLoggedIn = this.tokenService.hasValidToken();

    if(!isLoggedIn) {
      this.tokenService.clearToken();
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}