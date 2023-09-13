import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from 'app/modelos/producto';
import { BehaviorSubject, Observable, catchError, of } from 'rxjs';

@Injectable()
export class ProductoService {
  productosURL="http://localhost:8080/Paseo2/rest/producto";
  
  

  constructor(private http:HttpClient) { }

  

  public getProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.productosURL).pipe(
     catchError((err: any) => {return of([])})
     );
     ;
  }

  public getProducto(id: number): Observable<Producto>{
    return this.http.get<Producto>(`http://localhost:8080/Paseo2/rest/producto/${id}`);
  }

  public createProducto(p:Producto): Observable<Producto>{
    return this.http.post<Producto>(this.productosURL,p);
  }

  public updateProducto(p:Producto):Observable<Producto>{
    return this.http.put<Producto>(this.productosURL,p);
  }

}
