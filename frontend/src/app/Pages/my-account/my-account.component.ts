import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Club } from 'src/app/Models/Club';
import { User } from 'src/app/Models/User';
import { UserClubService } from 'src/app/Services/user-club.service';
import { UserEventService } from 'src/app/Services/user-event.service';
import { UserService } from 'src/app/Services/user.service';
import { ClubService } from 'src/app/services/club.service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent {

  id: string = '';
  user : User | undefined;
  events : any[] = [];
  clubs : any[] = [];

  constructor(private route: ActivatedRoute , private userService : UserService , private userEventService : UserEventService , private userClubService : UserClubService) { }


  ngOnInit(): void {
    //get id from current user in local storage
    this.id = JSON.parse(localStorage.getItem('currentUser')!).id;

    this.userService.getUser(Number(this.id)).subscribe((res: any) => {
      this.user = res;
    });

    this.userEventService.getAllEventsByUserId(Number(this.id)).subscribe((res: any) => {
      this.events = res;
    });

    this.userClubService.getUserClubsByUserId(Number(this.id)).subscribe((res: any) => {
      this.clubs = res;
    });




  }


  moveTo(id : any){
    //scroll to the element
    document.getElementById(id)?.scrollIntoView({behavior: 'smooth'});
  }

}
