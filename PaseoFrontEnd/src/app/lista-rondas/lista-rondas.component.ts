import { Component, OnInit } from '@angular/core';
import { Ronda } from 'app/modelos/ronda';
import { RondaService } from '../service/ronda.service';
import { Usuario } from 'app/modelos/usuario';
import { Estado } from 'app/utils/estado';
import { NavigationExtras, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UsuarioService } from 'app/service/usuario.service';

@Component({
  selector: 'app-lista-rondas',
  templateUrl: './lista-rondas.component.html',
  styleUrls: ['./lista-rondas.component.css']
})
export class ListaRondasComponent implements OnInit{

  user:Usuario;
  rondas: Ronda[];

  ngOnInit(): void {
    this.obtenerRondas();
  }

  constructor(private rondaService:RondaService,private router: Router,private toastr: ToastrService,private usuarioService:UsuarioService){}

  obtenerRondas(){
    const state = window.history.state;
    const userWithoutNavigationId = JSON.parse(JSON.stringify(state));
    delete userWithoutNavigationId.navigationId;
    this.user = userWithoutNavigationId;

    this.rondaService.getRondas().subscribe((response)=>{
        this.rondas=response;
        console.log(response);
        //ordeno por fecha asc
        this.rondas.sort(function (a, b) {
          // A va primero que B
          if (a.fechaInicio < b.fechaInicio)
              return 1;
          // B va primero que A
          else if (a.fechaInicio > b.fechaInicio)
              return -1;
          // A y B son iguales
          else 
              return 0;
        });
    });
  }

  estadoSeleccionado: Estado;
  estadoRonda = [
   { valor: Estado.ACTIVO, texto: 'Activo' },
   { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];

  display = "none";
  openModal() {
    this.display = "block";
  }
  onCloseHandled() {
    this.display = "none";
  }

  cambiarEstadoRonda(r:Ronda){
    this.rondaService.updateRonda(r).subscribe();
  }

  Editar(r:Ronda){
    const parametros: NavigationExtras = {
      state: r
    };
    this.router.navigate(['editarRonda'],parametros);
  }

  onSubmit(r:{fechainicio:Date,fechafin:Date,fecharetiro:Date,rangohorario:string}){
     let ronda=new Ronda(r.fechainicio,r.fechafin,r.fecharetiro,r.rangohorario,[],this.estadoSeleccionado);
     this.rondaService.createRonda(ronda).subscribe((response)=>{
        console.log(response);
        this.toastr.success('Ronda agregado correctamente', 'Éxito');
        this.router.navigate(['listaRondas']);
        this.onCloseHandled();
        this.obtenerRondas();
     });
  }
  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
