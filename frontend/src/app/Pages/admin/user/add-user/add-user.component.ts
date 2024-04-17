import { Component, Output , EventEmitter } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { Field } from 'src/app/Models/Field';
import { User } from 'src/app/Models/User';
import { FieldService } from 'src/app/Services/field.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {


  @Output() closeModal = new EventEmitter<boolean>();
  @Output() addUserSuccess = new EventEmitter<void>();


  user!: any;
  fields : Field[] = [];

  firstName : string = '';
  lastName : string = '';
  email : string = '';
  password : string = '';
  birthDate : string = '';
  role : string = '';
  verified : string = '';
  field : number = 1;
  


  constructor(private userService : UserService , private fieldService : FieldService,  private toast : NgToastService) { }


  ngOnInit(): void {

    this.fieldService.getAllFields().subscribe((res: any) => {
      this.fields = res;

    });

  }
  close(){
    this.closeModal.emit(false);
  }


  addUser(){


    this.user = {
      firstName : this.firstName,
      lastName : this.lastName,
      email : this.email,
      password : this.password,
      birthDate : this.birthDate as any,
      role : this.role,
      verified : true,
      fieldId : this.field,
    }
    this.userService.createUser(this.user).subscribe((res: any) => {
      this.toast.success({detail : 'Success' , summary: 'User Added' , duration: 2000});
      this.addUserSuccess.emit();
      this.close();
    } , (error) => {
      let errors = error.error.errors.join('\n');
      this.toast.error({detail : 'Error' , summary: errors , duration: 2000});
    });
  }


  

}
