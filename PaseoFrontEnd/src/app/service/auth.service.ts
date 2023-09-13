import { Injectable } from '@angular/core';
import { UsuarioService } from './usuario.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private usuarioService: UsuarioService,private http: HttpClient) { }
  
  private baseUrl = 'http://localhost:8080/Paseo2/rest/usuarios';

  currentUser: any;
  
  login(email: string, password: string): Observable<any> {
    const url = `${this.baseUrl}/login`; // Reemplaza con la ruta correcta de tu endpoint de verificación de inicio de sesión
    const data = { email, password };
    return this.http.post<any>(url, data);
  }

  logout(): void {
    this.currentUser = null;
  }

  getCurrentUser(): any {
    return this.currentUser;
  }
}
