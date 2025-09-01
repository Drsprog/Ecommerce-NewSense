import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, catchError, Observable, tap, throwError, timeout } from 'rxjs';
import { RegisterRequest } from '../models/register-request.interface';
import { AuthResponse } from '../models/auth-response.model';

interface LoginRequest {
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';

  // 🔹 Estado global de autenticación (true/false)
  private loggedIn = new BehaviorSubject<boolean>(
    !!localStorage.getItem('token')
  );
  isLoggedIn$ = this.loggedIn.asObservable();

  constructor(private http: HttpClient) {}

  login(credentials: LoginRequest): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.apiUrl}/login`, credentials)
      .pipe(
        timeout(5000),
        tap((res) => {
          if (res?.token) {
            this.saveToken(res.token);
            this.loggedIn.next(true); // 🔹 notifica al navbar
          }
        }),
        catchError((err) => {
          console.error('Error en AuthService:', err);
          return throwError(() => err);
        })
      );
  }

  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, data).pipe(
      tap((res) => {
        if (res?.token) {
          this.saveToken(res.token);
          this.loggedIn.next(true); // 🔹 también notifica al navbar
        }
      })
    );
  }

  // guardar token en localStorage
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // leer si el usuario está logueado
  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  // cerrar sesión
  logout(): void {
    localStorage.removeItem('token');
  }
}
