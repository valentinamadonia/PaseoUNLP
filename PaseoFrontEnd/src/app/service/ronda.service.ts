import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ronda } from 'app/modelos/ronda';
import { Observable, catchError, of } from 'rxjs';

@Injectable()
export class RondaService {
  rondaURL="http://localhost:8080/Paseo2/rest/ronda";

  constructor(private http:HttpClient) { }


  public getRondas(): Observable<Ronda[]>{
    return this.http.get<Ronda[]>(this.rondaURL).pipe(
     catchError((err: any) => {return of([])})
     );
    ;
  }

  public getRonda(id: number): Observable<Ronda>{
    return this.http.get<Ronda>(`http://localhost:8080/Paseo2/rest/ronda/${id}`);
  }

  public createRonda(r:Ronda): Observable<Ronda>{
    return this.http.post<Ronda>(this.rondaURL,r);
  }

  public updateRonda(r:Ronda):Observable<Ronda>{
    return this.http.put<Ronda>(this.rondaURL,r);
  }
}
