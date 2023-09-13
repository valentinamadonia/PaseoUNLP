import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Usuario } from '../modelos/usuario';
import { UsuarioService } from 'app/service/usuario.service';
import { Producto } from 'app/modelos/producto';
import { PedidoService } from 'app/service/pedido.service';
import { ProductoService } from 'app/service/producto.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Rol } from 'app/utils/rol';
import { RubroDeProductoService } from 'app/service/rubro-de-producto.service';
import { RubroDeProducto } from 'app/modelos/rubro-de-producto';
import { Estado } from 'app/utils/estado';


@Component({
  selector: 'app-ppal-user',
  templateUrl: './ppal-user.component.html',
  styleUrls: ['./ppal-user.component.css']
})
export class PpalUserComponent implements OnInit {
  verCarrito:boolean=false;
  public user:Usuario;
  public producto:Producto;
  public productos: Producto[];
  p:number=1;
  itemsPerPage:number=6;
  totalProductos:any;
  pedidoId:number;
  rubros:RubroDeProducto[]=[];
  rubroSeleccionado:string;
  
  constructor(private route: ActivatedRoute,private router: Router,private usuarioService: UsuarioService,private productoService: ProductoService,private pedidoService:PedidoService,private rubroService:RubroDeProductoService){
  
  }
 
  ngOnInit():void {
    this.obtenerProductos();
    this.obtenerRubros();

    //uso de cookie
    if(this.usuarioService.isUserLoggedIn()){
        const usuarioGuardado = this.usuarioService.getUser();
        if (usuarioGuardado) {
          this.user=usuarioGuardado;
          console.log('Usuario guardado:', usuarioGuardado);
        }
    }
    //

    this.pedidoService.pedidoIdObservable$.subscribe((nuevoValor) => {
      console.log('Nuevo valor de pedidoId:', nuevoValor);
      this.pedidoId=nuevoValor;
    });
  }

  productosFiltrados: Producto[]=[];
  filtarPorRubro(){
      this.productosFiltrados=[];
      if(this.rubroSeleccionado==='Todos'){
        this.productosFiltrados=this.productos;
      }else{
        const rubro = this.rubros.find(rubro => rubro.nombre === this.rubroSeleccionado);
        this.productos.forEach((productoA,i)=>{
          const entro = productoA.rubros.some(r => r.id === rubro?.id);
          if (entro) {
            this.productosFiltrados.push(productoA);
          }
        });
        
      }
  }

  obtenerProductos(){
     this.productoService.getProductos().subscribe((producto)=>{
         this.productos=producto;
         this.productos = this.productos.filter((p) => p.estado !== Estado.SUSPENDIDO);
         this.productosFiltrados=producto;
         this.totalProductos=this.productos.length;
     });
  }

  obtenerRubros(){
    this.rubroService.getRubros().subscribe((response)=>{
      this.rubros=response;
      this.rubros = this.rubros.filter((r) => r.estado !== Estado.SUSPENDIDO);
    });
  }

  editar(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['editarUser'],parametros);
  }

  historial(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['historialPedidos'],parametros);

  }

  mostrarCarrito(){
    this.verCarrito=!this.verCarrito;
  }

  agregarAlCarrito(producto:Producto){
    if(this.pedidoId===0){

      console.log('entro repetir');
     return this.pedidoService.agregarProducto(producto);
    }else{
      return this.pedidoService.agregarProductoMod(producto);
    }
  }

  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}

