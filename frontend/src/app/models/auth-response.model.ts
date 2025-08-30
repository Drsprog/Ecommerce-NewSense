export interface AuthResponse {
  token: string;
  username?: string;
  email?: string;
  roles?: string[]; // roles en el backend
}
