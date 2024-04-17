import { Component, EventEmitter, Output } from '@angular/core';

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

  closeAddEvent() {
    this.close.emit(false);
  }


  AddEvent() {

    const event = {
      name: this.eventName,
      description: this.eventDescription,
      number_places: this.numberPlaces,
      start_date: this.startDate,
      end_date: this.endDate,
      start_time: this.startTime,
      end_time: this.endTime,
      status: this.status
    }

    console.log(event);
  }

}
