import { Component } from '@angular/core';
import { Pedido } from 'app/modelos/pedido';
import { Usuario } from 'app/modelos/usuario';
import { PedidoService } from 'app/service/pedido.service';
import { } from '@fortawesome/free-solid-svg-icons';
import { EstadoPedido } from 'app/utils/estado-pedido';
import { UsuarioService } from 'app/service/usuario.service';
import { Router } from '@angular/router';
import { Estado } from 'app/utils/estado';
@Component({
  selector: 'app-lista-pedidos',
  templateUrl: './lista-pedidos.component.html',
  styleUrls: ['./lista-pedidos.component.css']
})
export class ListaPedidosComponent {
  public user:Usuario;
  pedidos:Pedido[]=[];

  ngOnInit():void {
    //uso de cookie
    if(this.usuarioService.isUserLoggedIn()){
      const usuarioGuardado = this.usuarioService.getUser();
      if (usuarioGuardado) {
        this.user=usuarioGuardado;
        console.log('Usuario guardado:', usuarioGuardado);
      }
    }
    //
    this.pedidoService.getPedidos().subscribe((response)=>{
        this.pedidos=response;
        this.pedidos.sort(function (a, b) {
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
        console.log(this.pedidos);
    });
  }

  constructor(private pedidoService:PedidoService,private usuarioService:UsuarioService,private router:Router){
  }

  confirmarPedido(p:Pedido){
    p.estado=EstadoPedido.CONFIRMADO;
    this.pedidoService.updatePedido(p).subscribe();
  }
  //modales
  userDisplay = "none";
  openModalUser() {
    this.userDisplay = "block";
  }
  onCloseHandledUser() {
    this.userDisplay = "none";
  }

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

  cambiarEstadoPedido(pedido:Pedido){
    this.pedidoService.updatePedido(pedido).subscribe();
  }

  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
