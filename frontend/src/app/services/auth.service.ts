import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../Models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {



  private baseUrl = 'http://localhost:9000/api/auth'; // Update the base URL as per your Spring Boot server

  constructor(private http: HttpClient) { }


  //login
  login(email: string, password: string) {
    return this.http.post(`${this.baseUrl}/login`, { email, password });
  }

  //register
  register(user : User) {
    return this.http.post(`${this.baseUrl}/register`, { user });
  }


}
