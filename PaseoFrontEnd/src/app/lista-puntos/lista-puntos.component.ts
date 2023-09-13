import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { PuntoDeRetiro } from 'app/modelos/punto-de-retiro';
import { Usuario } from 'app/modelos/usuario';
import { PuntoDeRetiroService } from 'app/service/punto-de-retiro.service';
import { UsuarioService } from 'app/service/usuario.service';
import { Estado } from 'app/utils/estado';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-lista-puntos',
  templateUrl: './lista-puntos.component.html',
  styleUrls: ['./lista-puntos.component.css']
})
export class ListaPuntosComponent implements OnInit{

  puntos:PuntoDeRetiro[];
  user:Usuario;
  
  constructor(private puntoService:PuntoDeRetiroService,private router: Router,private toastr: ToastrService,private usuarioService:UsuarioService){}

  ngOnInit(): void {
    const state = window.history.state;
    const userWithoutNavigationId = JSON.parse(JSON.stringify(state));
    delete userWithoutNavigationId.navigationId;
    this.user = userWithoutNavigationId;
    this.obtenerPuntos();
  }

  obtenerPuntos(){
     this.puntoService.getPuntos().subscribe((response)=>{
         this.puntos=response;
     });
  }

  cambiarEstadoPunto(punto:PuntoDeRetiro){
    this.puntoService.updatePunto(punto).subscribe();
  }

  estadoSeleccionado: Estado;
  estadoPunto = [
   { valor: Estado.ACTIVO, texto: 'Activo' },
   { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];

  Editar(punto:PuntoDeRetiro){
    const parametros: NavigationExtras = {
      state: punto
    };
    this.router.navigate(['editarPunto'],parametros);
  }

  display = "none";
  openModal() {
    this.display = "block";
  }
  onCloseHandled() {
    this.display = "none";
  }

  onSubmit(p:{nombre:string,direccion:string}){
     let punto= new PuntoDeRetiro(p.direccion,p.nombre,this.estadoSeleccionado);
     this.puntoService.createPunto(punto).subscribe((response)=>{
      console.log(response);
      this.toastr.success('Punto de retiro agregado correctamente', 'Éxito');
      this.router.navigate(['listaPuntos']);
      this.onCloseHandled();
      this.obtenerPuntos();
     });
  }

  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
