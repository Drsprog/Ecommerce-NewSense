import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { NgIf } from '@angular/common';
import { AuthResponse } from '../../models/auth-response.model';
import { Router } from '@angular/router';
import { LoginRequest } from '../../models/login-request.model';

@Component({
  selector: 'app-login',
  imports: [FormsModule, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  error: string = '';
  isLoading: boolean = false;

  constructor(private authService: AuthService, private router:Router) {}

  login() {

    this.error = '';

    if (!this.email || !this.password) {
      this.error = 'Por favor, completa todos los campos.';
      return;
    }

    this.isLoading = true;
    const credentials:LoginRequest = {
      email: this.email,
      password: this.password
    };

    this.authService.login(credentials).subscribe({
      next: (response:AuthResponse) => {
        this.isLoading = false;
        console.log('Login successful', response);
        // Aquí podrías guardar el token
        localStorage.setItem('token', response.token);
        // Redirigir a otra página si es necesario
        this.router.navigate(['/home']);
      },
      error: (err) => {
      this.isLoading = false;
      console.error('Login failed', err);
      this.error = err.error?.message || 'Error al iniciar sesión.';
      }
    });
  }

}
