export interface AuthResponse {
  token: string;
  username?: string;
  email?: string;
  roles?: string[]; // si usas roles en el backend
  // agrega aquí lo que te devuelva tu backend
}
