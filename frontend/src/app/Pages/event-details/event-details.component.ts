import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { EventService } from 'src/app/Services/event.service';
import { UserEventService } from 'src/app/Services/user-event.service';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent {

  id: string = '';
  event: any;
  user: any = JSON.parse(localStorage.getItem('currentUser')!);
  amIAttending: boolean = false;

  constructor(private route: ActivatedRoute, private eventSerivce: EventService, private userEventService: UserEventService, private toast: NgToastService) {
    this.id = this.route.snapshot.paramMap.get('id') || '';

  }

  ngOnInit(): void {
    this.isThisUserAttending();
    this.eventSerivce.getEventById(parseInt(this.id)).subscribe((res: any) => {
      this.event = res;
    });
    this.getEventsAttending();
    


  }

  attendEvent(id: any) {
    const userEvent = {
      user: {
        id: this.user.id
      },
      event: {
        id: id
      },
      date: new Date()
    }


    if(this.event.attendees > this.event.number_places){
      this.toast.error({ detail: 'Event is full', summary: 'Sorry', duration: 2000 });
      return;
    }
    this.userEventService.createEvent(userEvent).subscribe((res: any) => {
      this.amIAttending = true;
      this.getEventsAttending();
  
      this.toast.success({ detail: 'Event Attended', summary: 'See you there !', duration: 2000 });
    });

  }




  isThisUserAttending() {
    this.userEventService.getByEventAndUser(this.id, this.user.id).subscribe((res: any) => {
      if (res.length > 0) {
        this.amIAttending = true;
      }
    });
  }

  getEventsAttending() {
    this.userEventService.getEventsByEventId(parseInt(this.id)).subscribe((res: any) => {
      this.event.attendees = res.length;
    });
  }
    









}
