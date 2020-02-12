import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RegisterPayload} from './register-payload';
import {Observable} from 'rxjs';
import {LoginPayload} from './login-payload';
import {JwtAutResponse} from './jwt-aut-response';
import {map} from 'rxjs/operators';
import {LocalStorageService} from 'ngx-webstorage';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = 'https://localhost:7070/api/v1/';

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService, private router: Router) {
  }

  register(registerPayload: RegisterPayload): Observable<any> {
    return this.httpClient.post(this.url + 'signup', registerPayload);
  }

  logout() {
    this.localStorageService.clear('authenticationToken');
    this.localStorageService.clear('username');
    this.router.navigateByUrl('');
  }

  login(loginPayload: LoginPayload): Observable<boolean> {
    return this.httpClient.post<JwtAutResponse>(this.url + 'login', loginPayload).pipe(map(data => {
      this.localStorageService.store('authenticationToken', data.authenticationToken);
      this.localStorageService.store('username', data.username);
      return true;
    }));
  }

  isAuthenticated(): boolean {
    return this.localStorageService.retrieve('username') != null;
  }

  isAdmin() {
    return this.localStorageService.retrieve('username') === 'admin';
  }
}

