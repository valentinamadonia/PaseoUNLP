import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Productor } from 'app/modelos/productor';
import { ProductorService } from 'app/service/productor.service';
import { Estado } from 'app/utils/estado';
import { ToastrService } from 'ngx-toastr';
import { ProductoService } from '../service/producto.service';
import { UsuarioService } from 'app/service/usuario.service';

@Component({
  selector: 'app-lista-productores',
  templateUrl: './lista-productores.component.html',
  styleUrls: ['./lista-productores.component.css']
})
export class ListaProductoresComponent implements OnInit {

  productores:Productor[];

  estadoSeleccionado: Estado;
  estadoProductor = [
   { valor: Estado.ACTIVO, texto: 'Activo' },
   { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];

  ngOnInit(): void {
    this.obtenerProductores();
  }

  constructor(private productorService:ProductorService,private route: ActivatedRoute,private router: Router,private toastr: ToastrService,private productoService:ProductoService,private usuarioService:UsuarioService){
  }

  obtenerProductores(){
    this.productorService.getProductores().subscribe((response)=>{
      this.productores=response;
    });
  }

  cambiarEstadoProductor(p:Productor){
    this.productorService.updateProductor(p).subscribe((response)=>{
    });
  }

  productDisplay = "none";
  openModalProduct(productor: Productor) {
    let nodoProductos = document.getElementById("modal-productos-listado")
    this.productoService.getProductos().subscribe((response)=>{
          if (!productor.productos) {
            productor.productos = []; // Inicializar productor.productos si es undefined
          }
         response.forEach((producto)=>{
             if(producto.productor.id===productor.id){
                productor.productos.push(producto);
             }
         });
         console.log(productor.productos);
         if (nodoProductos && productor && productor.productos) {
          nodoProductos.innerHTML = "";
          productor.productos.forEach((producto)=>{
            let nodoHijo = document.createElement("div");
            nodoHijo.innerHTML = producto.nombre+ ", " + producto.descripcion;
            nodoProductos?.appendChild(nodoHijo);
          });
          this.productDisplay = "block";
        }
        if (nodoProductos && productor.productos.length === 0) {
          nodoProductos.innerHTML = "No hay productos";
          this.productDisplay = "block";
        }
        productor.productos = [];
    });
    
  }

  onCloseHandledProduct() {
    this.productDisplay = "none";
  }

  Editar(p:Productor){
    const parametros: NavigationExtras = {
      state: p
    };
    this.router.navigate(['editarProductor'],parametros);
  }

  display = "none";
  openModal() {
    this.display = "block";
  }
  onCloseHandled() {
    this.display = "none";
  }

  onSubmit(p:{nombre:string,descripcion:string}){
     let productor= new Productor(p.nombre,p.descripcion,this.estadoSeleccionado,[],[]);
     this.productorService.createProductor(productor).subscribe((response)=>{
          this.toastr.success('Productor agregado correctamente', 'Éxito');
          this.router.navigate(['listaProductores']);
          this.onCloseHandled();
          this.obtenerProductores();
     });
  }

  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
