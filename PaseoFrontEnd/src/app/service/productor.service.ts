import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Productor } from 'app/modelos/productor';
import { RubroDeProducto } from 'app/modelos/rubro-de-producto';
import { Observable, catchError, of } from 'rxjs';

@Injectable()
export class ProductorService {

  productoresURL="http://localhost:8080/Paseo2/rest/productor";

  constructor(private http:HttpClient) { }


  public getProductores(): Observable<Productor[]>{
    return this.http.get<Productor[]>(this.productoresURL).pipe(
     catchError((err: any) => {return of([])})
     );
    ;
  }

  public getProductor(id: number): Observable<Productor>{
    return this.http.get<Productor>(`http://localhost:8080/Paseo2/rest/productor/${id}`);
  }

  public createProductor(r:Productor): Observable<Productor>{
    return this.http.post<Productor>(this.productoresURL,r);
  }

  public updateProductor(r:Productor):Observable<Productor>{
    return this.http.put<Productor>(this.productoresURL,r);
  }
}
