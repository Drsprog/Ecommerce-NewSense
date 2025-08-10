import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { RegisterRequest } from '../../models/register-request.interface';


@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  form:RegisterRequest={
    username: '',
    email: '',
    password: '',
  }

  errorMessage = '';
  successMessage = '';
  isLoading = false;

  constructor(private authService: AuthService) {}

  onSubmit() {
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.form.username || !this.form.email || !this.form.password) {
      this.errorMessage = 'Todos los campos son obligatorios.';
      return;
    }

    this.isLoading = true;

    this.authService.register(this.form).subscribe({
      next: () => {
        this.successMessage = 'Usuario registrado exitosamente.';
        this.form = { username: '', email: '', password: '' }; // Reset form
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = err.error.message || 'Error al registrar el usuario.';
        this.isLoading = false;
      }
    });
    console.log('Datos enviados:', this.form);
  }
}
