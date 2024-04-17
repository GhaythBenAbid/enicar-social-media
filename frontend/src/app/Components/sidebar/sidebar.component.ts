import { Component, Input } from '@angular/core';
import { Club } from 'src/app/Models/Club';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  clubs : Club[] = [];
  user : any;

  showSideBar : boolean = false;
  @Input() showSideBarInput : boolean = false;

  constructor(private userService : UserService) {
    this.user = JSON.parse(localStorage.getItem('currentUser')!);
  }

  ngOnInit(): void {
    this.userService.getClubsResponsible(this.user.id).subscribe((res: any) => {
      this.clubs = res;
    });
  }

  closeSideBar(){
    this.showSideBar = false;
  }

}
