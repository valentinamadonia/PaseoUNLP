import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { Usuario } from 'app/modelos/usuario';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-ppal-admin',
  templateUrl: './ppal-admin.component.html',
  styleUrls: ['./ppal-admin.component.css']
})
export class PpalAdminComponent implements OnInit {
 
  public user:Usuario;

  constructor(private router: Router,private usuarioService:UsuarioService){}

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
  }
  
  listaUsuarios(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['listaUser'],parametros);
  }

  listaPedidos(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['listaPedidos'],parametros);
  }

  listaProductores(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['listaProductores'],parametros);
  }

  listaRubros(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['listaRubros'],parametros);
  }

  listaProductos(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['listaProductos'],parametros);
  }

  editar(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['editarUser'],parametros);
  }

  listaRondas(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['listaRondas'],parametros);
  }

  listaPuntos(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['listaPuntos'],parametros);
  }
  
  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
