import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  imports: [CommonModule, FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent {
  user: User = {
    username: '',
    email: '',
    role: '',
  };
  loading = true;
  error = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getProfile().subscribe({
      next: (data) => {
        this.user = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudo cargar el perfil';
        this.loading = false;
      },
    });
  }

  updateProfile() {
    this.userService.updateProfile(this.user).subscribe({
      next: () => alert('Perfil actualizado'),
      error: () => alert('Error al actualizar perfil'),
    });
  }
}
