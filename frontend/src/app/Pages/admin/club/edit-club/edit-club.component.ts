import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Club } from 'src/app/Models/Club';
import { User } from 'src/app/Models/User';
import { UserService } from 'src/app/Services/user.service';
import { ClubService } from 'src/app/services/club.service';

@Component({
  selector: 'app-edit-club',
  templateUrl: './edit-club.component.html',
  styleUrls: ['./edit-club.component.css']
})
export class EditClubComponent {
  
  id: any;
  club! : Club;
  users : User[] = [];


  newuser! : any;


  constructor(private route: ActivatedRoute , private clubService : ClubService ,  private toast : NgToastService , private userService : UserService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');

    this.clubService.getClubById(this.id).subscribe((res: any) => {
      this.club = res;
    });

    this.userService.getAllUsers().subscribe((res: any) => {
      this.users = res;
    });
  }

  updateUser(){
    this.clubService.updateClub(this.id , this.club).subscribe((res: any) => {
      this.toast.success({detail : 'Success' , summary: 'Club Updated' , duration: 2000});
    });
  }

}
