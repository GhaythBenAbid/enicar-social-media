import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { User } from 'src/app/Models/User';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  firstName: string = '';
  lastName: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  birthDate: string = '';
  field: string = '';

  constructor(private authService: AuthService, private toast: NgToastService) { }

  register() {
    if (this.password != this.confirmPassword) {
      this.toast.error({ detail: "ERROR", summary: 'Passwords do not match!', duration: 3000 });
      return;
    }
    //in success, navigate to login page and in error, show error message
    this.authService.register({
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
      birthDate: this.birthDate,
    } as User).subscribe((res: any) => {
      console.log(res);
      this.toast.success({ detail: "SUCCESS", summary: 'User Registered Successfully!', duration: 1000 });
      //after one second, navigate to login page
      setTimeout(() => {
        window.location.href = '/login';
      }, 1000);
    }, (error) => {
      //split array of errors into string with each error in new line
      let errors = error.error.errors.join('\n');
        this.toast.error({ detail: "ERROR", summary: error.error.errors, duration: 3000 });
    });
  }









}
