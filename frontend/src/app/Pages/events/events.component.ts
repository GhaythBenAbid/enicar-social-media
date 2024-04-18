import { Component } from '@angular/core';
import { Event } from 'src/app/Models/Event';
import { EventService } from 'src/app/Services/event.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent {

  events: Event[] = [];


  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.eventService.getAllEvents().subscribe((response: any) => {
      this.events = response;
    });


  }

  calculateDiff(start_date: string, end_date: string): number {
    let currentDate = new Date();
    let eventStartDate = new Date(start_date);
    let eventEndDate = new Date(end_date);


    return Math.ceil((eventEndDate.getTime() - eventStartDate.getTime()) / (1000 * 3600 * 24));
  }


}
