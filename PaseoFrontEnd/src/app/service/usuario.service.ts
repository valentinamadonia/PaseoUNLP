import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../modelos/usuario';
import { BehaviorSubject, Observable, ReplaySubject} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators'
import { of } from 'rxjs';
import { Rol } from 'app/utils/rol';
import { Estado } from 'app/utils/estado';
import { CookieService } from "ngx-cookie-service";

const httpOptions = {
  headers: new HttpHeaders({
  'Content-Type': 'application/json',
  'Authorization': 'my-auth-token',
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'Content-Type',
  'Access-Control-Allow-Methods': 'POST',
  })
 };
 
@Injectable()
export class UsuarioService {
 

  usersURL="http://localhost:8080/Paseo2/rest/usuarios";

  constructor(private http:HttpClient,private cookies: CookieService) { }

  private usuario: Usuario;
 

  setUser(u: Usuario) {
    const userStr = JSON.stringify(u);
    this.cookies.set("user", userStr);
  }

  clearUser() {
    this.cookies.delete("user");
  }

  isUserLoggedIn(): boolean {
    const userStr = this.cookies.get("user");
    return userStr !== null && userStr.trim() !== "";
  }

  getUser(): Usuario | null {
    const userStr = this.cookies.get("user");
    if (userStr) {
      return JSON.parse(userStr) as Usuario;
    }
    return null;
  }

  public getUsuarios(): Observable<Usuario[]>{
     return this.http.get<Usuario[]>(this.usersURL).pipe(
      catchError((err: any) => {return of([])})
      );
     ;
  }

  public getUsuario(id: number): Observable<Usuario>{
    return this.http.get<Usuario>(`http://localhost:8080/Paseo2/rest/usuarios/${id}`);
  }

  public getUsuarioEmail(email: string): Observable<Usuario>{
    return this.http.get<Usuario>(`http://localhost:8080/Paseo2/rest/usuarios/email/${email}`);
  }

  public createUsuario(user:Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(this.usersURL,user);
  }

  public updateUsuario(user:Usuario):Observable<Usuario>{
    return this.http.put<Usuario>(this.usersURL,user);
  }

}
