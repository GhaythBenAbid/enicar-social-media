import { Component } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';


@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent {

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin],
    eventColor: '#1b70b6',
    eventTextColor: '#ffffff',
    events: "https://fullcalendar.io/api/demo-feeds/events.json",
    editable: true, // Enable drag and drop
    droppable: true // Enable drop events onto the calendar
  };
};
