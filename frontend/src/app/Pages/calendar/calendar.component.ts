import { Component } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import { Event } from 'src/app/Models/Event';
import { EventService } from 'src/app/Services/event.service';


@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent {

  events: Event[] = [];
  calendarEvents: any[] = [];

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.eventService.getAllEvents().subscribe((response: any) => {
      this.events = response;
      this.events.forEach(event => {
        console.log(event);
        this.calendarOptions.events = [{
          title: event.name,
          start: event.start_date,
          end: event.end_date,
        }];
      });
    });
  }


  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin],
    eventColor: '#1b70b6',
    eventTextColor: '#ffffff',
    events: [
      
    ],
    editable: true, // Enable drag and drop
    droppable: true // Enable drop events onto the calendar
  };
};
