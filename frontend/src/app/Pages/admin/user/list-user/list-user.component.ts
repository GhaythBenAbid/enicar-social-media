import { Component } from '@angular/core';
import { User } from 'src/app/Models/User';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent {

  users : User[] = [];


  constructor(private userService : UserService) { }


  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((res: any) => {
      console.log(res);
      this.users = res;
    });
  }






}
