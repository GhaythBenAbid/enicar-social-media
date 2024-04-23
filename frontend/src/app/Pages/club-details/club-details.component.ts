import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Club } from 'src/app/Models/Club';
import { Event } from 'src/app/Models/Event';
import { UserClubService } from 'src/app/Services/user-club.service';
import { ClubService } from 'src/app/services/club.service';

@Component({
  selector: 'app-club-details',
  templateUrl: './club-details.component.html',
  styleUrls: ['./club-details.component.css']
})
export class ClubDetailsComponent {

  id: string = '';
  club: Club | undefined;
  addEventModal: boolean = false;
  currentRole: string = '';
  events: Event[] = [];
  status: string = '';
  user: any = JSON.parse(localStorage.getItem('currentUser')!);

  constructor(private route: ActivatedRoute, private clubService: ClubService, private userClubService: UserClubService, private toast: NgToastService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id') || '';

    this.clubService.getEventsByClubId(parseInt(this.id)).subscribe((res: any) => {
      this.events = res;
    });




    this.clubService.getClubById(parseInt(this.id)).subscribe((res: any) => {
      this.club = res;
      if (this.user.role == 'admin') {
        this.currentRole = 'admin';
      } else if (this.club?.responsible.id === this.user.id) {
        this.currentRole = 'responsible';
      }


    });



    this.userClubService.getUserClubByUserIdAndClubId(this.user.id, parseInt(this.id)).subscribe((res: any) => {

      this.status = res[0].status;
    });







  }

  closeModal() {
    this.addEventModal = false;
    this.clubService.getEventsByClubId(parseInt(this.id)).subscribe((res: any) => {
      this.events = res;
    });



  }

  joinClub() {
    const userClub = {
      user: {
        id: this.user.id
      },
      club: {
        id: parseInt(this.id)
      },
      status: 'pending'
    }



    this.userClubService.createUserClub(userClub).subscribe((res: any) => {
      this.toast.success({ detail: 'Request Sent', summary: 'Success', duration: 2000 });
    });

  }




  moveTo(id: any) {
    //scroll to the element
    document.getElementById(id)?.scrollIntoView({ behavior: 'smooth' });
  }




}
