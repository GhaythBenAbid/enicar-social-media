import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { NgToastService } from 'ng-angular-popup';
import { User } from 'src/app/Models/User';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {

  id : any;
  user : User | undefined;
  password : string = '';
  confirmPassword : string = '';
  token : string = '';

  constructor(private userService : UserService , private toast : NgToastService) { }

  ngOnInit(): void {
    const searchParams = new URLSearchParams(window.location.search);
    this.token = searchParams.get('token') || '';
    console.log("TOKEN" , this.token);
    this.id = jwtDecode(this.token).sub;
    console.log("ID" , this.id);

    this.userService.getUser(this.id).subscribe((res: any) => {
      this.user = res;
    });
  }

  reset(){
    if(this.password !== this.confirmPassword){
      this.toast.error({detail : 'Password do not match' , summary: 'Error' , duration: 2000});
      return;
    }

    this.user!.password = this.password;
    this.userService.updateUser(this.id , this.user!).subscribe((res: any) => {
      this.toast.success({detail : 'Success' , summary: 'Password Reset' , duration: 2000});

      //after 2 seconds redirect to login page
      setTimeout(() => {
        window.location.href = '/login';
      }, 2000);

    });
      
  }

  

}
