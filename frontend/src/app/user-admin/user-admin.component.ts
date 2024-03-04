import { Component, OnInit } from '@angular/core';
import { User } from '../Models/User';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-user-admin',
  templateUrl: './user-admin.component.html',
  styleUrls: ['./user-admin.component.css']
})
export class UserAdminComponent implements OnInit {

  users: User[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }


  loadUsers(): void {
    this.userService.getAllUsers().subscribe((users) => {
      this.users = users;
      console.log(users);
    }, error => {
      console.error('Error fetching users:', error);
    });
  }

  addUser(user: User): void {
    this.userService.createUser(user).subscribe(response => {
      console.log('User added:', response);
      this.loadUsers(); // Reload the list to include the new user
    }, error => {
      console.error('Error adding user:', error);
    });
  }

  editUser(userID: number, updatedUser: User): void {
    this.userService.updateUser(userID, updatedUser).subscribe(response => {
      console.log('User updated:', response);
      this.loadUsers(); // Reload the list to reflect the changes
    }, error => {
      console.error('Error updating user:', error);
    });
  }

  deleteUser(userID: number): void {
    this.userService.deleteUser(userID).subscribe(response => {
      console.log('User deleted:', response);
      this.loadUsers(); // Reload the list to exclude the deleted user
    }, error => {
      console.error('Error deleting user:', error);
    });
  }

  // Implement other methods as needed
}
