import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserPayload} from './source-users/user-payload';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private url = 'http://localhost:7070/api/v1/users';

  constructor(private httpClient: HttpClient) { }

  getAllUsers(): Observable<Array<UserPayload>> {
    return this.httpClient.get<Array<UserPayload>>(this.url);
  }

  deleteUser(permaLink: number): Observable<any> {
    return this.httpClient.delete(this.url + '/' + permaLink);
  }

  getUsername(username: string): Observable<Array<UserPayload>> {
    return this.httpClient.get<Array<UserPayload>>(this.url + '/username/' + username);
  }
}
