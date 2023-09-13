import { Component, OnInit } from '@angular/core';
import { PuntoDeRetiro } from 'app/modelos/punto-de-retiro';
import { Estado } from 'app/utils/estado';
import { PuntoDeRetiroService } from '../service/punto-de-retiro.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UsuarioService } from 'app/service/usuario.service';

@Component({
  selector: 'app-editar-punto',
  templateUrl: './editar-punto.component.html',
  styleUrls: ['./editar-punto.component.css']
})
export class EditarPuntoComponent implements OnInit{
  
  punto:PuntoDeRetiro;
  estadoSeleccionado: Estado;
  estadoPunto= [
    { valor: Estado.ACTIVO, texto: 'Activo' },
    { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];

  constructor(private puntoService:PuntoDeRetiroService,private router: Router,private toastr: ToastrService,private usuarioService:UsuarioService){}

  ngOnInit(): void {
    const state = window.history.state;
    const userWithoutNavigationId = JSON.parse(JSON.stringify(state));
    delete userWithoutNavigationId.navigationId;
    this.punto = userWithoutNavigationId;
    this.estadoSeleccionado=this.punto.estado;
  }

  onSubmit(){
     this.punto.estado=this.estadoSeleccionado;
     this.puntoService.updatePunto(this.punto).subscribe((response)=>{
      this.punto=response;
      this.router.navigate(['listaPuntos']);
      this.toastr.success('Punto de retiro editado con éxito');
     });
  }
  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
