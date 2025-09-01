import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,tap } from 'rxjs';
import { AuthResponse } from '../models/auth-response.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl= 'http://localhost:8080/user';

  constructor(private http:HttpClient, private authService: AuthService) { }

  getProfile(): Observable<any> {
    return this.http.get(`${this.apiUrl}/me`);
  }

  updateProfile(data: any): Observable<AuthResponse> {
    return this.http.put<AuthResponse>(`${this.apiUrl}/me`, data).pipe(
    tap((res) => {
      if (res?.token) {
        this.authService.saveToken(res.token); // guardamos token nuevo
      }
    })
  );
  }

  updatePassword(data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/me/password`, data);
  }

}
