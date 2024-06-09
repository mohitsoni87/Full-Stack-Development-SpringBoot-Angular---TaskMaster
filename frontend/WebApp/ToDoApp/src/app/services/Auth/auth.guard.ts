import { ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../Authentication/authentication.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot, 
  state: RouterStateSnapshot) => {

    const authService = inject(AuthenticationService);
    const router = inject(Router);
    const isUserLoggedIn = authService.isUserLoggedIn();
    if(!isUserLoggedIn){
      console.log("Not logged in");
      router.navigate(['/login'])
      return false;
    }
    return true;
};