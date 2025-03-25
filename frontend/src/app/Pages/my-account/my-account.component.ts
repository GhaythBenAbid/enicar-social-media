import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Club } from 'src/app/Models/Club';
import { User } from 'src/app/Models/User';
import { UserService } from 'src/app/Services/user.service';
import { ClubService } from 'src/app/services/club.service';
import { Post } from 'src/app/Models/Post';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent {

  id: string = '';
  user : User | undefined;
  posts: Post[] = [];

  constructor(private route: ActivatedRoute , private userService : UserService) { }


  ngOnInit(): void {
    //get id from current user in local storage
    this.id = JSON.parse(localStorage.getItem('currentUser')!).id;

    this.userService.getUser(Number(this.id)).subscribe((res: any) => {
      this.user = res;
    });



  }


  moveTo(id : any){
    //scroll to the element
    document.getElementById(id)?.scrollIntoView({behavior: 'smooth'});
  }

}
