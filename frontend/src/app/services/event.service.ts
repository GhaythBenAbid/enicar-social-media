import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private baseUrl = 'http://localhost:9000/api/event'; // Update the base URL as per your Spring Boot server
  private headers = { 'Authorization': `Bearer ${localStorage.getItem('token')}` };

  constructor(private http: HttpClient) { }

  
  createEvent(event: any): any {
    return this.http.post(`${this.baseUrl}`, event , { headers: this.headers });
  }

  getAllEvents(): any {
    return this.http.get<Event[]>(`${this.baseUrl}` , { headers: this.headers });
  }

  getEventById(id: number): any {
    return this.http.get(`${this.baseUrl}/${id}` , { headers: this.headers });
  }

  updateEvent(id: number, event: any): any {
    return this.http.put(`${this.baseUrl}/${id}`, event);
  }

  deleteEvent(id: number): any {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

}
