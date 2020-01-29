import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {LoginPayload} from '../login-payload';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginPayload: LoginPayload;

  constructor(private authService: AuthService, private router: Router) {
    this.loginForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    });
    this.loginPayload = {
      username: '',
      password: ''
    };
  }

  ngOnInit() {
  }

  onSubmit() {
    this.loginPayload.username = this.loginForm.get('username').value;
    this.loginPayload.password = this.loginForm.get('password').value;

    this.authService.login(this.loginPayload).subscribe(data => {
      if (data) {
        this.router.navigateByUrl('');
      } else {
        console.log('login failed');
      }
    });
  }

  assertValidate() {
    if (this.loginForm.get('username').value && this.loginForm.get('password').value) {
      return this.loginForm.get('username').value;
    }
    return '';
  }
}
