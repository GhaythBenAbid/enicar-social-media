import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {

  message: any;

  constructor(private http: HttpClient) {
  }

  async callBackend() {
    await this.http.get('localhost:9000/api/club').subscribe((data) => {
      this.message = data;
    });
    
  }

}
