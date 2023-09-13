import { Component, OnInit } from '@angular/core';
import { Usuario } from '../modelos/usuario';
import { UsuarioService } from '../service/usuario.service';
import { Rol } from '../utils/rol';
import { Estado } from '../utils/estado';
import { NavigationExtras, Router } from '@angular/router';
import { Producto } from 'app/modelos/producto';
import { ProductoService } from '../service/producto.service';


@Component({
  selector: 'app-usuario-home',
  templateUrl: './usuario-home.component.html',
  styleUrls: ['./usuario-home.component.css'],
  providers: [UsuarioService]
})
export class UsuarioHomeComponent implements OnInit {
  public productos: Producto[];
  p:number=1;
  itemsPerPage:number=3;


  constructor(private productoService:ProductoService) {
  }

  ngOnInit(): void {
    this.obtenerProductos();
  }
  obtenerProductos(){
    this.productoService.getProductos().subscribe((producto)=>{
        this.productos=producto;
        this.productos = this.productos.filter((p) => p.estado !== Estado.SUSPENDIDO);
        console.log('productos',this.productos);
       // this.totalProductos=this.productos.length;
    });
 }


}
