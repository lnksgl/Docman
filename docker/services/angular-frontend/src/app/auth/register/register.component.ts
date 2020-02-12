import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {RegisterPayload} from '../register-payload';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  registerPayload: RegisterPayload;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.formBuilder.group({
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    });
    this.registerPayload = {
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
  }

  ngOnInit() {
  }

  onSubmit() {
    this.registerPayload.username = this.registerForm.get('username').value;
    this.registerPayload.email = this.registerForm.get('email').value;
    this.registerPayload.password = this.registerForm.get('password').value;
    this.registerPayload.confirmPassword = this.registerForm.get('confirmPassword').value;

    if (this.registerPayload.password === this.registerPayload.confirmPassword) {
      this.authService.register(this.registerPayload).subscribe(data => {
          this.router.navigateByUrl('v1/login');
        }, error => {
          console.log('Error');
        }
      );
    } else {
      console.log('Error');
    }
  }

  assertValidate(username: string, email: string, password: string, confirmPassword: string) {
    return !(username != '' && email != '' && password != '' && confirmPassword != '');
  }
}
