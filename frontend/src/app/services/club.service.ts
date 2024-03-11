import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Club } from '../Models/Club';

@Injectable({
  providedIn: 'root'
})
export class ClubService {


  private baseUrl = 'http://localhost:9000/api/club'; // Update the base URL as per your Spring Boot server

  constructor(private http: HttpClient) { }

  createClub(club: Club): Observable<any> {
    return this.http.post(`${this.baseUrl}`, club);
  }

  getAllClubs(): Observable<Club[]> {
    return this.http.get<Club[]>(`${this.baseUrl}`);
  }

  getClubById(id: number): Observable<Club> {
    return this.http.get<Club>(`${this.baseUrl}/${id}`);
  }

  updateClub(id: number, club: Club): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, club);
  }

  deleteClub(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
