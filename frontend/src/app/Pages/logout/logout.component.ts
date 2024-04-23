import { Component } from '@angular/core';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent {
  
    constructor() { }
  
    ngOnInit() {
      localStorage.removeItem('currentUser');
      localStorage.removeItem('token');
      window.location.href = '/login';
    }


}
