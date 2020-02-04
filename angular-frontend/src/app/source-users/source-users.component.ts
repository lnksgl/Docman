import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {UsersService} from '../users.service';
import {UserPayload} from './user-payload';

@Component({
  selector: 'app-source-users',
  templateUrl: './source-users.component.html',
  styleUrls: ['./source-users.component.css']
})
export class SourceUsersComponent implements OnInit {

  page: number = 1;
  user: UserPayload;

  users: Observable<Array<UserPayload>>;

  constructor(private userService: UsersService) {
  }

  ngOnInit() {
    this.users = this.userService.getAllUsers();
  }

  deleteUser(id) {
    this.userService.deleteUser(id).subscribe();
  }

  searchUser(value: string) {
    if (value === '') {
      this.ngOnInit();
    } else {
      this.users = this.userService.getUsername(value);
    }
  }
}
