import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { User } from 'src/app/Models/User';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-verifcation',
  templateUrl: './verifcation.component.html',
  styleUrls: ['./verifcation.component.css']
})
export class VerifcationComponent {


  id : any;
  user : User | undefined;

  token : string = '';
  constructor(private route: ActivatedRoute , private userService : UserService) { }

  ngOnInit(): void {
    this.token = this.route.snapshot.paramMap.get('token') || '';
    this.id = jwtDecode(this.token).sub;

    this.userService.getUser(this.id).subscribe((res: any) => {
      this.user = res;
    });
  }

  verify(){
    this.user!.verified = true;
    this.userService.updateUser(this.id , this.user!).subscribe((res: any) => {
      console.log(res);
      window.location.href = '/login';
    });
  }

  

}
