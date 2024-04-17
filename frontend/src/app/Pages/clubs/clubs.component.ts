import { Component } from '@angular/core';
import { Club } from 'src/app/Models/Club';
import { ClubService } from 'src/app/services/club.service';

@Component({
  selector: 'app-clubs',
  templateUrl: './clubs.component.html',
  styleUrls: ['./clubs.component.css']
})
export class ClubsComponent {
  
  clubs: Club[] = [];
  
  constructor(private clubService: ClubService) { }


  ngOnInit(): void {
    this.clubService.getAllClubs().subscribe((data) => {
      this.clubs = data;
    });
  }


}
