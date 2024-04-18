import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from 'src/app/Services/event.service';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent {

  eventName: string = '';
  eventDescription: string = '';
  startDate: string = '';
  endDate: string = '';
  startTime: string = '';
  endTime: string = '';
  numberPlaces: number = 0;
  status: string = '';

  @Output() close = new EventEmitter<boolean>();
  id = this.route.snapshot.paramMap.get('id') || '';
  

  closeAddEvent() {
    this.close.emit(false);
  }

  constructor(private eventService : EventService , private route : ActivatedRoute) { }


  AddEvent() {

    const event = {
      name: this.eventName,
      description: this.eventDescription,
      number_places: this.numberPlaces,
      start_date: this.startDate,
      end_date: this.endDate,
      start_time: this.startTime,
      end_time: this.endTime,
      status: this.status,
      club: {
        id: this.id
      }
    }

    this.eventService.createEvent(event).subscribe((response: any) => {
      this.closeAddEvent();
    });


  }

}
