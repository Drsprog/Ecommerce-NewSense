import { Component } from '@angular/core';
import {FormBuilder,FormGroup,Validators,ReactiveFormsModule} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthResponse } from '../../models/auth-response.model';
import { LoginRequest } from '../../models/login-request.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm: FormGroup;
  error = '';
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  login() {
    this.error = '';

    if (this.loginForm.invalid) {
      this.error = 'Por favor, completa todos los campos correctamente.';
      return;
    }

    this.isLoading = true;
    const credentials: LoginRequest = this.loginForm.value;

    this.authService.login(credentials).subscribe({
      next: (response: AuthResponse) => {
        this.isLoading = false;
        console.log('Login successful', response);

        // Guardar token en localStorage
        localStorage.setItem('token', response.token);

        // Redirigir
        this.router.navigate(['/home']);
      },
      error: (err) => {
        this.isLoading = false;
        console.error('Login failed', err);
        this.error = err.error?.message || 'Error al iniciar sesión.';
      },
    });
  }
}
