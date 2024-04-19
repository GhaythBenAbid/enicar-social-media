import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Club } from 'src/app/Models/Club';
import { Event } from 'src/app/Models/Event';
import { ClubService } from 'src/app/services/club.service';

@Component({
  selector: 'app-club-details',
  templateUrl: './club-details.component.html',
  styleUrls: ['./club-details.component.css']
})
export class ClubDetailsComponent {

  id: string = '';
  club: Club | undefined;
  addEventModal : boolean = false;
  currentRole: string = '';
  events: Event[] = [];
  user: any = JSON.parse(localStorage.getItem('currentUser')!);

  constructor(private route: ActivatedRoute, private clubService: ClubService) { }


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






  }


  moveTo(id: any) {
    //scroll to the element
    document.getElementById(id)?.scrollIntoView({ behavior: 'smooth' });
  }




}
