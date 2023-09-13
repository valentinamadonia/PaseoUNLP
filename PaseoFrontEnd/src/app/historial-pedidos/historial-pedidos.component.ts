import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { Pedido } from 'app/modelos/pedido';
import { Usuario } from 'app/modelos/usuario';
import { PedidoService } from '../service/pedido.service';
import { EstadoPedido } from 'app/utils/estado-pedido';
import { ProductoEncargado } from '../modelos/producto-encargado';
import { Producto } from 'app/modelos/producto';
import { Estado } from 'app/utils/estado';
import { UsuarioService } from 'app/service/usuario.service';

@Component({
  selector: 'app-historial-pedidos',
  templateUrl: './historial-pedidos.component.html',
  styleUrls: ['./historial-pedidos.component.css']
})
export class HistorialPedidosComponent implements OnInit{

  user:Usuario; 
  pedidoIdObservable$ = this.pedidoService.pedidoIdObservable$;
  misPedidos:Pedido[];
  pedidos:Pedido[];
  carrito: ProductoEncargado[]=[];
  pedidoId:number;

  constructor(private router: Router,private pedidoService:PedidoService,private usuarioService:UsuarioService){
  }

  ngOnInit(): void {
    //uso de cookie
    if(this.usuarioService.isUserLoggedIn()){
      const usuarioGuardado = this.usuarioService.getUser();
      if (usuarioGuardado) {
        this.user=usuarioGuardado;
        console.log('Usuario guardado:', usuarioGuardado);
      }
    }
    //
    this.misPedidos=[];
    this.obtenerPedidos();
    
    this.pedidoService.carritoObservable$.subscribe((carrito) => {
      this.carrito=carrito;
    });

  }

  obtenerPedidos(){
      this.pedidoService.getPedidos().subscribe((response)=>{
          this.pedidos=response;
          console.log('pedidos2',this.pedidos);
          this.pedidos.forEach((pedidoA,i)=>{
            const entro = pedidoA.usuario.id===this.user.id;
            if (entro) {
              this.misPedidos.push(pedidoA);
            }
          });
          console.log('mis pedidos',this.misPedidos);
          //ordeno por fecha asc
          this.misPedidos.sort(function (a, b) {
            // A va primero que B
            if (a.fecha < b.fecha)
                return 1;
            // B va primero que A
            else if (a.fecha > b.fecha)
                return -1;
            // A y B son iguales
            else 
                return 0;
          });
      });
  }

  inicio(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['ppalUser'],parametros);
  }



  //modal
  productDisplay = "none";
  openModalProduct(pedido: Pedido) {
    let nodoProductos = document.getElementById("modal-productos-listado")
    if (nodoProductos) {
      nodoProductos.innerHTML = "";
      pedido.listaProductos.forEach((productoEncargado)=>{
        if(productoEncargado.estado===Estado.ACTIVO){
          let nodoHijo = document.createElement("div");
          nodoHijo.innerHTML = productoEncargado.cantidad + " x " + productoEncargado.producto.nombre + ", productor: " + productoEncargado.producto.productor.nombre;
          nodoProductos?.appendChild(nodoHijo);
        }
      })
      this.productDisplay = "block";
    }
  }

  onCloseHandledProduct() {
    this.productDisplay = "none";
  }

  cancelarPedido(pedido:Pedido){
     pedido.estado=EstadoPedido.CANCELADO;
     this.pedidoService.updatePedido(pedido).subscribe();
  }

  repetirPedido(pedido:Pedido){
    //vacio carrito
    for (const producto of this.carrito) {
      this.eliminarProducto(producto.producto);
    }
    this.carrito=[];
    //agrego productoss
    pedido.listaProductos.forEach((productoE,i)=>{
      for(i=0; i<productoE.cantidad;i++){
       this.pedidoService.agregarProducto(productoE.producto);
      }
    });
    console.log(' historial repetir',this.carrito);
    const parametros: NavigationExtras = {
      state: this.user
     
    };
    this.router.navigate(['ppalUser'],parametros);  
  }
  
  eliminarProducto(producto:Producto){
    this.pedidoService.eliminarProducto(producto);
  }

  modificarPedido(pedido:Pedido){
    console.log('id mod',pedido.id);
    //vacio carrito
    for (const producto of this.carrito) {
      this.eliminarProducto(producto.producto);
    }
    this.carrito=[];
    //agrego productos
    pedido.listaProductos.forEach((productoE,i)=>{
        this.pedidoService.agregarProducto2(productoE);
    });
    //envio el pedido a modificar
    this.pedidoService.setPedidoId(pedido.id);
    const parametros: NavigationExtras = {
      state: this.user
      
    };
    this.router.navigate(['ppalUser'],parametros);  
  }

  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
