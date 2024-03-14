import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Field } from '../Models/Field';

@Injectable({
  providedIn: 'root'
})
export class FieldService {
  private baseUrl = 'http://localhost:9000/api/field';
  constructor(private http: HttpClient) { }

  createField(field: Field): Observable<any> {
    return this.http.post(`${this.baseUrl}`, field);
  }

  updateField(fieldID: number, field: Field): Observable<any> {
    return this.http.put(`${this.baseUrl}/${fieldID}`, field);
  }

  getField(fieldID: number): Observable<Field> {
    return this.http.get<Field>(`${this.baseUrl}/${fieldID}`);
  }

  getAllFields(): Observable<Field[]> {
    return this.http.get<Field[]>(`${this.baseUrl}`);
  }

  deleteField(fieldID: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${fieldID}`);
  }
}
