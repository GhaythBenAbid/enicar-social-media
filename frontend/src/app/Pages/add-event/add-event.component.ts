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
  banner: File | undefined;

  @Output() close = new EventEmitter<boolean>();
  id = this.route.snapshot.paramMap.get('id') || '';
  

  closeAddEvent() {
    this.close.emit(false);
  }

  constructor(private eventService : EventService , private route : ActivatedRoute) { }


  onFileSelected(event: any) {
    this.banner = event.target.files[0];
  }

  AddEvent() {

    const event = new FormData();
    event.append('name', this.eventName);
    event.append('description', this.eventDescription);
    event.append('number_places', this.numberPlaces.toString());
    event.append('start_date', this.startDate);
    event.append('end_date', this.endDate);
    event.append('start_time', this.startTime);
    event.append('end_time', this.endTime);
    event.append('status', this.status);
    event.append('Banner', this.banner!);
    event.append('club', this.id);


    this.eventService.createEvent(event).subscribe((response: any) => {
      this.closeAddEvent();
    });


  }

}
