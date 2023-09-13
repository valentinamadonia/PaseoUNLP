import { Component, OnInit } from '@angular/core';
import { Producto } from 'app/modelos/producto';
import { ProductoService } from '../service/producto.service';
import { ProductorService } from 'app/service/productor.service';
import { RubroDeProductoService } from 'app/service/rubro-de-producto.service';
import { Productor } from 'app/modelos/productor';
import { RubroDeProducto } from 'app/modelos/rubro-de-producto';
import { Estado } from 'app/utils/estado';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UsuarioService } from 'app/service/usuario.service';

@Component({
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html',
  styleUrls: ['./editar-producto.component.css']
})
export class EditarProductoComponent implements OnInit{

  producto:Producto;
  productores:Productor[];
  rubros:RubroDeProducto[];
  rubrosSeleccionados: RubroDeProducto[] = [];
  productorSeleccionado:Productor;
  estadoSeleccionado: Estado;
  estadoProducto = [
   { valor: Estado.ACTIVO, texto: 'Activo' },
   { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];
  
  imagenSeleccionada:string;

  ngOnInit() {
    const state = window.history.state;
    const productoWithoutNavigationId = JSON.parse(JSON.stringify(state));
    delete productoWithoutNavigationId.navigationId;
    this.producto = productoWithoutNavigationId;
    this.imagenSeleccionada=this.producto.imagenes[0].imagen;
    this.producto.rubros.forEach((rubro)=>{
      this.rubrosSeleccionados.push(rubro);
    });
    console.log(this.rubrosSeleccionados);
    this.estadoSeleccionado=this.producto.estado;
    this.obtenerProductores();
    this.obtenerRubros();
  }


  constructor(private productoService:ProductoService,private productorService:ProductorService,private rubroService:RubroDeProductoService,private router: Router,private toastr: ToastrService,private usuarioService:UsuarioService){}


  rubroEstaSeleccionado(rubro: RubroDeProducto): boolean {
    return this.rubrosSeleccionados.some((rubroSeleccionado) => rubroSeleccionado.id === rubro.id);
  }

  obtenerProductores(){
    this.productorService.getProductores().subscribe((response)=>{
      this.productores=response;
      if (this.productores && this.producto.productor) {
        this.productores.find((productor)=>{
          if(productor.id === this.producto.productor.id){
            this.productorSeleccionado=productor;
          }
        });
      }
    });
  }

  obtenerRubros(){
    this.rubroService.getRubros().subscribe((response)=>{
      this.rubros=response;
    });
  }

  agregarRubro(r:RubroDeProducto){
    const pos = this.rubrosSeleccionados.findIndex((rubro) => rubro.id === r.id);
    if(pos===-1){
      this.rubrosSeleccionados.push(r);
      console.log('rubro agregado',this.rubrosSeleccionados);
    }else{
      this.rubrosSeleccionados.splice(pos,1);
      console.log('eliminar rubro',this.rubrosSeleccionados);
    }
  }

  onSubmit(){
    this.producto.rubros=this.rubrosSeleccionados;
    this.producto.productor=this.productorSeleccionado;
    console.log(this.imagenSeleccionada);
    if (this.producto.imagenes.length > 0) {
      this.producto.imagenes[0].imagen = this.imagenSeleccionada;
    }
    this.producto.estado=this.estadoSeleccionado;
    this.productoService.updateProducto(this.producto).subscribe((response)=>{
      this.producto=response;
      this.router.navigate(['listaProductos']);
      this.toastr.success('Producto editado con éxito');
      });
  }
  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }

}
