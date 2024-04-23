import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserEventService {

    private baseUrl = 'http://localhost:9000/api/userEvent'; // Update the base URL as per your Spring Boot server
    private headers = { 'Authorization': `Bearer ${localStorage.getItem('token')}` };

    constructor(private http: HttpClient) { }

    createEvent(event: any): Observable<any> {
        return this.http.post(`${this.baseUrl}`, event , { headers: this.headers });
    }

    getEventsByUserId(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}`);
    }

    deleteEvent(id: any): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}` , { headers: this.headers });
    }

    getEventById(id: any): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}` , { headers: this.headers });
    }

    getByEventAndUser(eventId: any, userId: any): Observable<any> {
        return this.http.get(`${this.baseUrl}/event/${eventId}/user/${userId}` , { headers: this.headers });
    }

    getEventsByEventId(eventId: any): Observable<any> {
        return this.http.get(`${this.baseUrl}/event/${eventId}` , { headers: this.headers });
    }

    getAllEventsByUserId(id: any): Observable<any> {
        return this.http.get(`${this.baseUrl}/user/${id}` , { headers: this.headers });
    }

    updateEvent(id: number, event: any): Observable<any> {
        return this.http.put(`${this.baseUrl}/${id}`, event);
    }






}
