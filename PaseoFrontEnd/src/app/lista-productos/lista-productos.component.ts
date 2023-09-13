import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../service/producto.service';
import { Producto } from 'app/modelos/producto';
import { Image } from 'app/modelos/image';
import { Estado } from 'app/utils/estado';
import { Productor } from 'app/modelos/productor';
import { RubroDeProducto } from 'app/modelos/rubro-de-producto';
import { RubroDeProductoService } from '../service/rubro-de-producto.service';
import { ProductorService } from 'app/service/productor.service';
import { NavigationExtras, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormGroup } from '@angular/forms';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-lista-productos',
  templateUrl: './lista-productos.component.html',
  styleUrls: ['./lista-productos.component.css']
})
export class ListaProductosComponent implements OnInit{
  
  productoForm: FormGroup; 
  productos: Producto[];
  rubros:RubroDeProducto[];
  productores: Productor[];

  productorSeleccionado:Productor;
  rubrosSeleccionados: RubroDeProducto[] = [];
  estadoSeleccionado: Estado;
  estadoProducto = [
   { valor: Estado.ACTIVO, texto: 'Activo' },
   { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];

  ngOnInit(): void {
    this.rubrosSeleccionados=[];
    this.obtenerProductos();
    this.obtenerRubros();
    this.obtenerProductores();
  }

  constructor(private productoService:ProductoService,private rubroService:RubroDeProductoService,private productorService:ProductorService,private router: Router,private toastr: ToastrService, private usuarioService:UsuarioService){}

  obtenerProductos(){
    this.productoService.getProductos().subscribe((response)=>{
        this.productos=response;
    });
  }

  obtenerRubros(){
    this.rubroService.getRubros().subscribe((response)=>{
        this.rubros=response;
    });
  }

  obtenerProductores(){
    this.productorService.getProductores().subscribe((response)=>{
      this.productores=response;
    });
  }

  cambiarEstado(p:Producto){
      this.productoService.updateProducto(p).subscribe();
  }

  agregarRubro(r:RubroDeProducto){
      let pos= this.rubrosSeleccionados.indexOf(r);
      if(pos===-1){
        this.rubrosSeleccionados.push(r);
        console.log('rubro agregado',this.rubrosSeleccionados);
      }else{
        this.rubrosSeleccionados.splice(pos,1);
        console.log('eliminar rubro',this.rubrosSeleccionados);
      }
  }
  
  Editar(producto:Producto){
    const parametros: NavigationExtras = {
      state: producto
    };
    this.router.navigate(['editarProducto'],parametros);
  }

  //modal
  display = "none";
  openModal() {
    this.display = "block";
  }
  onCloseHandled() {
    this.display = "none";
  }

  imagenes: Image[] = [];
  onSubmit(p:{nombre:string,descripcion:string,stock:number,precio:number,imagen:string}){
    
        let i=new Image(p.nombre,p.imagen);
        this.imagenes.push(i);
        console.log('imagen',i.imagen);
        console.log('imagen nombre',i.nombre);
        console.log('imagen2',this.imagenes[0].imagen);
        console.log('imagen nombre2',this.imagenes[0].nombre);
        console.log('rubros',this.rubrosSeleccionados);
        let productoNuevo= new Producto(this.rubrosSeleccionados,p.nombre,p.stock,p.descripcion,p.precio,this.estadoSeleccionado,this.productorSeleccionado,this.imagenes);
        this.productoService.createProducto(productoNuevo).subscribe((response)=>{
          console.log(response);
          this.toastr.success('Producto agregado correctamente', 'Éxito');
          this.router.navigate(['listaProductos']);
          this.onCloseHandled();
          this.obtenerProductos();
        },
        (error) => {
          console.error('Error al crear el pedido:', error);
          this.toastr.error('Error al crear el pedido: ' + error.message);
        });
        this.rubrosSeleccionados=[];
        this.imagenes.splice(0, 1);
    }

    cerrarSesion(){
      this.usuarioService.clearUser();
      this.router.navigate(['home']);
    }
}
