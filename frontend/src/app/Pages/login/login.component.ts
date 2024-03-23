import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
    
    email: string = '';
    password: string = '';

    constructor(private authService: AuthService , private toast: NgToastService) { }


    login() {
        //in success, navigate to homepage and in error, show error message
        this.authService.login(this.email, this.password).subscribe((res: any) => {
            localStorage.setItem('currentUser', JSON.stringify(res.user));
            localStorage.setItem('token', res.token);
            console.log(res);
            this.toast.success({detail:"SUCCESS",summary:'User Logged In Successfully!',duration:1000});
            //after one second, navigate to homepage
            setTimeout(() => {
                window.location.href = '/feed';
            }, 1000);
        }, (error) => {
            this.toast.error({detail:"ERROR",summary:error.error.errors,duration:3000});
        });
    }

}
