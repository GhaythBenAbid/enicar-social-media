import { Component } from '@angular/core';
import { User } from 'src/app/Models/User';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent {


  user! : User;

  firstName : string = '';
  lastName : string = '';
  email : string = '';
  birthDate : string = '';

  constructor() { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser')!);

    this.firstName = this.user.firstName;
    this.lastName = this.user.lastName;
    this.email = this.user.email;
    this.birthDate = this.user.birthDate.toISOString();
  }



}
