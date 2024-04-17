import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  
  private baseUrl = 'http://localhost:9000/api/post'; // Update the base URL as per your Spring Boot server
  private headers = { 'Authorization': `Bearer ${localStorage.getItem('token')}` };

  constructor(private http: HttpClient) { }

  createPost(post: any): any {
    return this.http.post(`${this.baseUrl}`, post , { headers: this.headers });
  }

  getAllPosts(): any {
    return this.http.get(`${this.baseUrl}` , { headers: this.headers });
  }

  getPostById(id: number): any {
    return this.http.get(`${this.baseUrl}/${id}` , { headers: this.headers });
  }

  updatePost(id: number, post: any): any {
    return this.http.put(`${this.baseUrl}/${id}`, post);
  }

  deletePost(id: number): any {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

}
