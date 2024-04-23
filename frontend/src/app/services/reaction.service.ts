import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReactionService {


  private baseUrl = 'http://localhost:9000/api/reaction'; // Update the base URL as per your Spring Boot server
  private headers = { 'Authorization': `Bearer ${localStorage.getItem('token')}` };

  constructor(private http: HttpClient) { }

  createReaction(reaction: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, reaction , { headers: this.headers });
  }

  getReactionsByPostId(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  deleteReaction(id: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}` , { headers: this.headers });
  }

  getReactionById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateReaction(id: number, reaction: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, reaction);
  }



}
