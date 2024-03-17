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

  addUserModal : boolean = false;
  deleteUserModal : number = 0;


  constructor(private userService : UserService) { }


  ngOnInit(): void {
    this.getAllUsers();
  }


  getAllUsers(){
    this.userService.getAllUsers().subscribe((res: any) => {
      console.log(res);
      this.users = res;
    });
  }



  toggleAddUserModal(status : boolean){
    this.addUserModal = status;
  }

  toggleDeleteUserModal(id : number){
    this.deleteUserModal = id;
  }









}
