import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { User } from 'src/app/Models/User';
import { UserService } from 'src/app/Services/user.service';

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
  aboutMe : string = '';
  photo : File | undefined;
  coverPhoto : File | undefined;
  verified : boolean = false;

  userphoto : string = '';
  usercoverPhoto : string = '';

  constructor(private userService: UserService , private toast: NgToastService) { }

  onFileSelectedCover(event: any) {
    this.coverPhoto = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.usercoverPhoto = reader.result as string;
    }
  }

  onFileSelectedPhoto(event: any) {
    this.photo = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.userphoto = reader.result as string;
    }
  }

  getUserDetails() {
    this.user = JSON.parse(localStorage.getItem('currentUser')!);

    this.firstName = this.user.firstName;
    this.lastName = this.user.lastName;
    this.email = this.user.email;
    this.birthDate = this.user.birthDate as unknown as string;
    this.aboutMe = this.user.aboutMe;
    this.userphoto = this.user.profilePicture;
    this.usercoverPhoto = this.user.coverPicture;
    this.verified = this.user.verified;
  }

  ngOnInit(): void {
    this.getUserDetails();
    
  }

  updateProfile() {
    const formData = new FormData();
    formData.append('firstName', this.firstName);
    formData.append('lastName', this.lastName);
    formData.append('email', this.email);
    formData.append('birthDate', this.birthDate);
    formData.append('aboutMe', this.aboutMe);
    formData.append('profilePicture', this.photo!);
    formData.append('coverPicture', this.coverPhoto!);
    formData.append('verified', this.verified.toString());


    this.userService.updateUser(this.user.id, formData).subscribe((res: any) => {
      localStorage.setItem('currentUser', JSON.stringify(res.user));
      this.getUserDetails();
      this.toast.success({detail:"SUCCESS",summary:'User Updated Successfully!',duration:1000});
    });

  }



}
