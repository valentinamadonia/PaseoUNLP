import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewChecked, AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Pedido } from 'app/modelos/pedido';
import { Producto } from 'app/modelos/producto';
import { ProductoEncargado } from 'app/modelos/producto-encargado';
import { PuntoDeRetiro } from 'app/modelos/punto-de-retiro';
import { Ronda } from 'app/modelos/ronda';
import { Usuario } from 'app/modelos/usuario';
import { PedidoService } from 'app/service/pedido.service';
import { ProductoEncargadoService } from 'app/service/producto-encargado.service';
import { PuntoDeRetiroService } from 'app/service/punto-de-retiro.service';
import { UsuarioService } from 'app/service/usuario.service';
import { Estado } from 'app/utils/estado';
import { EstadoPedido } from 'app/utils/estado-pedido';
import { RetiroEntrega } from 'app/utils/retiro-entrega';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements  OnInit{

  user:Usuario;
  carrito: ProductoEncargado[]=[];
  pedidoId:number=0;
  puntoId:PuntoDeRetiro;
  pedidoModificado:Pedido;
  rangoHorario:string;

  pedidoForm: NgForm;
  
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
    this.puntoDeRetiroService.getPuntos().subscribe(puntos => {
      this.puntosDeRetiro=puntos;
      this.puntosDeRetiro = this.puntosDeRetiro.filter((p) => p.estado !== Estado.SUSPENDIDO);
    });


    //si hay un pedido a modificar recibo los datos
    this.pedidoService.pedidoIdObservable$.subscribe((nuevoValor) => {
      this.pedidoId=nuevoValor;
      if(this.pedidoId!==0){
        this.pedidoService.getPedido(this.pedidoId).subscribe((response)=>{
            this.pedidoModificado=response;
            this.retiroentregaSeleccionado=response.retiroEntrega;
            this.puntoId=response.direccionRetiro;
            
        });
    }
    });

    this.pedidoService.carritoObservable$.subscribe((carrito) => {
      this.cantProductos = carrito.length;
      this.carrito=carrito;
      if(this.carrito.length===0){
        this.pedidoService.setPedidoId(0);
      }
    });
    
  }
  pedidoIdObservable$ = this.pedidoService.pedidoIdObservable$;
  carritoObservable$ = this.pedidoService.carritoObservable$;
  puntosDeRetiro: PuntoDeRetiro[]=[];
  cantProductos:number;

  //Formulario
  retiroentrega = [
    { valor: RetiroEntrega.RETIRO, texto: 'Retiro' },
    { valor: RetiroEntrega.ENTREGA, texto: 'Entrega' }
  ];

  retiroentregaSeleccionado:RetiroEntrega;
  mostrarCamposRetiro: boolean = false;
  mostrarCamposEntrega: boolean = false;

  actualizarCamposRetiroEntrega() {
    if (this.retiroentregaSeleccionado === RetiroEntrega.RETIRO) {
      this.mostrarCamposRetiro = true;
      this.mostrarCamposEntrega = false;
    } else if (this.retiroentregaSeleccionado === RetiroEntrega.ENTREGA) {
      this.mostrarCamposRetiro = false;
      this.mostrarCamposEntrega = true;
    } else {
      this.mostrarCamposRetiro = false;
      this.mostrarCamposEntrega = false;
    }
  }

  onSubmit(p:{puntoId:PuntoDeRetiro,direccionEntrega:string,rangoHorario:string}){
    if(this.pedidoId===0){
        let ronda: Ronda | null = null;
        console.log('user',this.user);
        let pedido= new Pedido(new Date(),new Date(),this.user,this.retiroentregaSeleccionado,EstadoPedido.NOCONFIRMADO,p.puntoId,p.direccionEntrega,this.totalCarrito(),p.rangoHorario,this.carrito,ronda);
        console.log('pedido antes=>',pedido);
        this.pedidoService.createPedido(pedido).subscribe((response)=>{
            pedido=response;
            console.log('pedido=',response);
            this.toastr.success('Compra creada con éxito');
        },
        (error) => {
          console.error('Error al crear el pedido:', error);
          this.toastr.error('Error al crear el pedido: ' + error.message);
        });
        //vacio carrito
        for (const producto of this.carrito) {
          this.eliminarProducto(producto.producto);
        }
        this.carrito=[];
        const parametros: NavigationExtras = {
          state: this.user
        };
        this.router.navigate(['ppalUser'],parametros);
     }else{
         console.log('K',this.carrito);
         this.pedidoModificado.id=this.pedidoId;
         this.pedidoModificado.listaProductos=this.carrito;
         console.log('K2',this.pedidoModificado.listaProductos);
         this.pedidoModificado.retiroEntrega=this.retiroentregaSeleccionado;
         this.pedidoModificado.direccionEntrega=p.direccionEntrega;
         this.pedidoModificado.direccionRetiro=p.puntoId;
         this.pedidoModificado.montoTotal=this.totalCarrito();
         this.pedidoModificado.rangoHorario=p.rangoHorario;
         this.pedidoService.modificarPedido(this.pedidoModificado).subscribe((response)=>{
            this.toastr.success('Compra modificada con éxito');
            this.pedidoService.setPedidoId(0);
            this.pedidoForm.resetForm();
            console.log('pedido modificado',response);
         });
          //vacio carrito
          for (const producto of this.carrito) {
            this.eliminarProducto(producto.producto);
          }
          this.carrito=[];
          this.pedidoModificado.id=0;
          this.pedidoModificado.listaProductos=[];
          this.pedidoModificado.direccionEntrega="";
          this.pedidoModificado.montoTotal=0;
          this.pedidoModificado.rangoHorario="";
          this.pedidoService.setPedidoId(0);
          const parametros: NavigationExtras = {
            state: this.user
          };
          this.router.navigate(['ppalUser'],parametros);
     }
    }

    

  //
  constructor(private cdr: ChangeDetectorRef,private pedidoService: PedidoService,private puntoDeRetiroService: PuntoDeRetiroService,private usuarioService:UsuarioService,private productoEncargadoService:ProductoEncargadoService,private route: ActivatedRoute,private router: Router,private toastr: ToastrService){}



  totalProducto(precio:number,cantidad:number){
     return precio*cantidad;
  }

  eliminarProducto(producto:Producto){
      this.pedidoService.eliminarProducto(producto);
      if(this.carrito.length===0){
        this.pedidoService.setPedidoId(0);
      }
  }

  updateCantidad(operacion:string,id:number){
    const productoE= this.pedidoService.find(id);
    if(productoE){
      if(operacion==="resta" && productoE.cantidad>0){
          productoE.cantidad--;
      }
      if(operacion==="suma" ){
          productoE.cantidad++;
      }
      if(productoE.cantidad===0){
         this.eliminarProducto(productoE.producto);
      }
    }
  }

  totalCarrito(){
       const result=this.pedidoService.totalCarrito();
       return result;
  }
}
