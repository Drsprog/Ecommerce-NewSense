import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError, timeout } from 'rxjs';
import { RegisterRequest } from '../models/register-request.interface';

interface LoginRequest {
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';
  constructor(private http: HttpClient) {}

  login(credentials: LoginRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials).pipe(
      timeout(5000),
      catchError((err) => {
        console.error('Error en AuthService:', err);
        return throwError(() => err);
      })
    );
  }

  register(data:RegisterRequest):Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data);
  }
}
