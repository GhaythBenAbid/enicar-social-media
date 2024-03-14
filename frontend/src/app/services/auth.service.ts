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
  register(user : any) {
    return this.http.post(`${this.baseUrl}/register`, {
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      password: user.password,
      birthDate: user.birthDate,
      role: 'STUDENT',
      fieldId: user.fieldId
    });
  }


}
