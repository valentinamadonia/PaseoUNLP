import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RubroDeProducto } from 'app/modelos/rubro-de-producto';
import { Observable, catchError, of } from 'rxjs';

@Injectable()
export class RubroDeProductoService {
  
  rubrosURL="http://localhost:8080/Paseo2/rest/rubrodeproducto";

  constructor(private http:HttpClient) { }


  public getRubros(): Observable<RubroDeProducto[]>{
    return this.http.get<RubroDeProducto[]>(this.rubrosURL).pipe(
     catchError((err: any) => {return of([])})
     );
    ;
  }

  public getRubro(id: number): Observable<RubroDeProducto>{
    return this.http.get<RubroDeProducto>("http://localhost:8080/Paseo2/rest/rubrodeproducto/${id}");
  }

  public createRubro(r:RubroDeProducto): Observable<RubroDeProducto>{
    return this.http.post<RubroDeProducto>(this.rubrosURL,r);
  }

  public updateRubro(r:RubroDeProducto):Observable<RubroDeProducto>{
    return this.http.put<RubroDeProducto>(this.rubrosURL,r);
  }


}
