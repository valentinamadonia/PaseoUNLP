import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pedido } from 'app/modelos/pedido';
import { Producto } from 'app/modelos/producto';
import { ProductoEncargado } from 'app/modelos/producto-encargado';
import { Estado } from 'app/utils/estado';
import { BehaviorSubject, Observable, catchError, of } from 'rxjs';

@Injectable()
export class PedidoService {
  pedidosURL="http://localhost:8080/Paseo2/rest/carrito";

  private carrito: ProductoEncargado[]=[];
  private carritoObservable=new BehaviorSubject<ProductoEncargado[]>([]);
  carritoObservable$= this.carritoObservable.asObservable();
  
  private pedidoId: number = 0;
  private pedidoIdObservable=new BehaviorSubject<number>(0);
  pedidoIdObservable$= this.pedidoIdObservable.asObservable();

  constructor(private http:HttpClient) { }

  setPedidoId(nuevoValor: number) {
    this.pedidoId = nuevoValor;
    this.pedidoIdObservable.next(this.pedidoId);
    console.log('peddido service',this.pedidoId);
  }

  getPedidoId(): number {
    return this.pedidoId;
  }
  
  agregarProducto(p:Producto){
    if(this.carrito.length===0){
      let pe=new ProductoEncargado(1,p,null,Estado.ACTIVO);
      this.carrito.push(pe);
      this.carritoObservable.next(this.carrito); //envio valores
    }else{
      const pModificado=this.carrito.find((productoEncargado)=>{
        return productoEncargado.producto.id===p.id;
      });
      if(pModificado){
        pModificado.cantidad++;
        this.carritoObservable.next(this.carrito); //envio valores
      }else{
        let pe=new ProductoEncargado(1,p,null,Estado.ACTIVO);
        this.carrito.push(pe);
        this.carritoObservable.next(this.carrito); //envio valores
      }
    }
    console.log('repetir2',this.carrito);
  }

  agregarProductoMod(p:Producto){
    let idProd;
    const pModificado=this.carrito.find((productoEncargado,indice)=>{
      idProd=indice;
      return productoEncargado.producto.id===p.id;
    });
    if(pModificado){//si ya existe en el carrito
      pModificado.cantidad++;
      this.carritoObservable.next(this.carrito); //envio valores
    }else{
      let pe=new ProductoEncargado(1,p,null,Estado.ACTIVO);
      this.carrito.push(pe);
      this.carritoObservable.next(this.carrito); //envio valores
    }
    console.log('mirar',this.carrito);
  }

  agregarProducto2(pe:ProductoEncargado){
    this.carrito.push(pe);
    this.carritoObservable.next(this.carrito); //envio valores
  }

  eliminarProducto(producto:Producto){
      this.carrito= this.carrito.filter((productoE)=>{
        return productoE.producto!=producto;
      });
      this.carritoObservable.next(this.carrito);
      if(this.carrito.length===0){
        this.setPedidoId(0);
      }
  }

  
  find(id:number){
     return this.carrito.find((productoE)=>{
        return productoE.producto.id ===id;
     });
  }

  totalCarrito(){
    const total= this.carrito.reduce(function(suma,productoE){
      return suma+(productoE.cantidad*productoE.producto.precio);
    },0);
    return total;
  }

  public getPedidos(): Observable<Pedido[]>{
    return this.http.get<Pedido[]>(this.pedidosURL).pipe(
     catchError((err: any) => {return of([])})
     );
    ;
  }

  public getPedido(id: number): Observable<Pedido>{
    return this.http.get<Pedido>(`http://localhost:8080/Paseo2/rest/carrito/${id}`);
  }

  public createPedido(p:Pedido): Observable<Pedido>{
    return this.http.post<Pedido>(this.pedidosURL,p);
  }

  public updatePedido(p:Pedido):Observable<Pedido>{
    return this.http.put<Pedido>(this.pedidosURL,p);
  }

  public modificarPedido(p:Pedido):Observable<Pedido>{
    return this.http.put<Pedido>("http://localhost:8080/Paseo2/rest/carrito/modificar",p);
  }

}
