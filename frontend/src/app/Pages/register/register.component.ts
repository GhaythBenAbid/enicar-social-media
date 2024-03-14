import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { Field } from 'src/app/Models/Field';
import { User } from 'src/app/Models/User';
import { AuthService } from 'src/app/Services/auth.service';
import { FieldService } from 'src/app/Services/field.service';

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
  selectedField: number = 1;

  fields : Field[] = [];




  constructor(private authService: AuthService, private fieldService : FieldService , private toast: NgToastService) { }

  ngOnInit(): void {
    this.fieldService.getAllFields().subscribe((res: any) => {
      this.fields = res;
    });
  }






  register() {
    if (this.password != this.confirmPassword) {
      this.toast.error({ detail: "ERROR", summary: 'Passwords do not match!', duration: 3000 });
      return;
    }
    this.authService.register({
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
      birthDate: this.birthDate,
      fieldId: this.selectedField
    }).subscribe((res: any) => {
      console.log(res);
      this.toast.success({ detail: "SUCCESS", summary: 'User Registered Successfully!', duration: 1000 });
      setTimeout(() => {
        // window.location.href = '/login';
      }, 1000);
    }, (error) => {
      let errors = error.error.errors.join('\n');
      this.toast.error({ detail: "ERROR", summary: errors, duration: 3000 });
    });
  }









}
