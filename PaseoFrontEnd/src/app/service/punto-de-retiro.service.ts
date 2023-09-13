import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PuntoDeRetiro } from 'app/modelos/punto-de-retiro';
import { Observable, catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PuntoDeRetiroService {
  puntosURL="http://localhost:8080/Paseo2/rest/puntoDeRetiro";

  constructor(private http:HttpClient) { }

  public getPuntos(): Observable<PuntoDeRetiro[]>{
    return this.http.get<PuntoDeRetiro[]>(this.puntosURL).pipe(
     catchError((err: any) => {return of([])})
     );
     ;
  }
  public getPunto(id: number): Observable<PuntoDeRetiro>{
    return this.http.get<PuntoDeRetiro>(`http://localhost:8080/Paseo2/rest/puntoDeRetiro/${id}`);
  }

  public createPunto(r:PuntoDeRetiro): Observable<PuntoDeRetiro>{
    return this.http.post<PuntoDeRetiro>(this.puntosURL,r);
  }

  public updatePunto(r:PuntoDeRetiro):Observable<PuntoDeRetiro>{
    return this.http.put<PuntoDeRetiro>(this.puntosURL,r);
  }
}
