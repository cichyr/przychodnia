import {Injectable} from '@angular/core'
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router'
import {Observable} from 'rxjs'
import {UserService} from './user.service'
import {environment} from '../../environments/environment'
import {Credentials} from '../data/user/credentials'
import {map, tap} from 'rxjs/operators'
import {dev_only_credentials} from '../../environments/auto.login.credentials'

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (this.userService.hasUser())
      return true

    if (!environment.production && environment.auto_auth)
      return this.userService
        .signIn(new Credentials(dev_only_credentials.username, dev_only_credentials.password))
        .pipe(map(user => !!user))
        .pipe(tap(user => this.router.navigate(['home'])))

    this.router.navigate(['login-page'])
    return false
  }
}
