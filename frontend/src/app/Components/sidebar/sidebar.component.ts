import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  showSideBar : boolean = false;
  @Input() showSideBarInput : boolean = false;

  constructor() { }
  

  closeSideBar(){
    this.showSideBar = false;
  }

}
