import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserClubService {

  private baseUrl = 'http://localhost:9000/api/user-club'; // Update the base URL as per your Spring Boot server
    private headers = { 'Authorization': `Bearer ${localStorage.getItem('token')}` };

  constructor(private http: HttpClient) { }

  createUserClub(userClub: any): any {
    return this.http.post(`${this.baseUrl}`, userClub , { headers: this.headers });
  }

  getUserClubsByUserId(id: number): any {
    return this.http.get(`${this.baseUrl}/user/${id}` , { headers: this.headers });
  }

  deleteUserClub(id: any): any {
    return this.http.delete(`${this.baseUrl}/${id}` , { headers: this.headers });
  }

  getUserClubById(id: number): any {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getUserClubByUserIdAndClubId(userId: number, clubId: number): any {
    return this.http.get(`${this.baseUrl}/${userId}/${clubId}` , { headers: this.headers });
  }

}
