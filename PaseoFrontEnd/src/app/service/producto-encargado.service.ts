import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductoEncargado } from 'app/modelos/producto-encargado';
import { Observable, catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoEncargadoService {
  productosURL="http://localhost:8080/Paseo2/rest/productoEncargado";
  constructor(private http:HttpClient) { }

  public getProductos(): Observable<ProductoEncargado[]>{
    return this.http.get<ProductoEncargado[]>(this.productosURL).pipe(
     catchError((err: any) => {return of([])})
     );
     ;
  }

  public getProducto(id: number): Observable<ProductoEncargado>{
    return this.http.get<ProductoEncargado>("http://localhost:8080/Paseo2/rest/productoEncargado/${id}");
  }

  public createProducto(p:ProductoEncargado): Observable<ProductoEncargado>{
    return this.http.post<ProductoEncargado>(this.productosURL,p);
  }

  public updateProducto(p:ProductoEncargado):Observable<ProductoEncargado>{
    return this.http.put<ProductoEncargado>(this.productosURL,p);
  }
}
