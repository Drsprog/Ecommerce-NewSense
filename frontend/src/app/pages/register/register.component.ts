import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {ReactiveFormsModule,FormBuilder,FormGroup,Validators} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router, RouterModule } from '@angular/router';
import { AuthResponse } from '../../models/auth-response.model';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registerForm: FormGroup;
  errorMessage = '';
  successMessage = '';
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    this.errorMessage = '';
    this.successMessage = '';

    if (this.registerForm.invalid) {
      this.errorMessage = 'Todos los campos son obligatorios y válidos.';
      return;
    }

    this.isLoading = true;

    this.authService.register(this.registerForm.value).subscribe({
      next: (response: AuthResponse) => {
        this.successMessage = 'Usuario registrado exitosamente 🎉';
        this.registerForm.reset(); // Reset form
        this.isLoading = false;

        // Guardar token si viene en la respuesta
        if (response.token) {
          this.authService.saveToken(response.token);
        }

        // Redirigir a home (o donde tengas el navbar)
        this.router.navigate(['/home']);
      },
      error: (err) => {
        this.errorMessage =
          err.error?.message || 'Error al registrar el usuario.';
        this.isLoading = false;
      },
    });

    console.log('Datos enviados:', this.registerForm.value);
  }
}
