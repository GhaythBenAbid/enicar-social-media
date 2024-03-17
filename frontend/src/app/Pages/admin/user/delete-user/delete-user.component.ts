import { Component , EventEmitter, Input, Output } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-delete-user',
  templateUrl: './delete-user.component.html',
  styleUrls: ['./delete-user.component.css']
})
export class DeleteUserComponent {


  @Input() user!: any;
  @Output() closeModal = new EventEmitter<boolean>();
  @Output() deleteUserSuccess = new EventEmitter<void>();


  constructor(private userService : UserService , private toast : NgToastService) { }

  close(){
    this.closeModal.emit(false);
  }

  deleteUser(){
    this.userService.deleteUser(this.user.id).subscribe((res: any) => {
      this.toast.success({detail : 'Success' , summary: 'User Deleted' , duration: 2000});
      this.close();
      this.deleteUserSuccess.emit();
    });
  }


}
