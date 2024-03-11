import { Component } from '@angular/core';
import { Club } from 'src/app/Models/Club';
import { ClubService } from 'src/app/services/club.service';

@Component({
  selector: 'app-list-club',
  templateUrl: './list-club.component.html',
  styleUrls: ['./list-club.component.css']
})
export class ListClubComponent {

  constructor(private clubService: ClubService) { }


  clubs: Club[] = [];

  ngOnInit(): void {
    this.getAllClubs();
  }

  getAllClubs() {
    this.clubService.getAllClubs().subscribe((data: Club[]) => {
      this.clubs = data;
    });
  }




}
