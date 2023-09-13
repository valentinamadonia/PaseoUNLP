import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ronda } from 'app/modelos/ronda';
import { RondaService } from 'app/service/ronda.service';
import { UsuarioService } from 'app/service/usuario.service';
import { Estado } from 'app/utils/estado';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-editar-ronda',
  templateUrl: './editar-ronda.component.html',
  styleUrls: ['./editar-ronda.component.css']
})
export class EditarRondaComponent implements OnInit{

  ronda:Ronda;
  estadoSeleccionado: Estado;
  estadoRonda = [
    { valor: Estado.ACTIVO, texto: 'Activo' },
    { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];
  formattedFechaInicio:string;
  formattedFechaFin:string;
  formattedFechaRetiro:string;
  constructor(private rondaService:RondaService,private router: Router,private toastr: ToastrService,private datePipe: DatePipe,private usuarioService:UsuarioService){
  }

  ngOnInit(): void {
    const state = window.history.state;
    const userWithoutNavigationId = JSON.parse(JSON.stringify(state));
    delete userWithoutNavigationId.navigationId;
    this.ronda = userWithoutNavigationId;
    this.ronda.fechaInicio = new Date(this.ronda.fechaInicio);
    this.formattedFechaInicio = this.ronda.fechaInicio.toISOString().split('T')[0];
    
    this.ronda.fechaFin = new Date(this.ronda.fechaFin);
    this.formattedFechaFin = this.ronda.fechaFin.toISOString().split('T')[0];

    this.ronda.fechaRetiro = new Date(this.ronda.fechaRetiro);
    this.formattedFechaRetiro = this.ronda.fechaRetiro.toISOString().split('T')[0];

    this.estadoSeleccionado=this.ronda.estado;
  }

  onSubmit(r:{fechainicio:Date,fechafin:Date,fecharetiro:Date,rangohorario:string}){
    this.ronda.fechaInicio=new Date(this.formattedFechaInicio);
    this.ronda.fechaFin=new Date(this.formattedFechaFin);
    this.ronda.fechaRetiro=new Date(this.formattedFechaRetiro);
    this.ronda.rangoHorario=r.rangohorario;
    this.ronda.estado=this.estadoSeleccionado;
    this.rondaService.updateRonda(this.ronda).subscribe((response)=>{
      this.ronda=response;
      console.log(response);
      this.router.navigate(['listaRondas']);
      this.toastr.success('Ronda editada con éxito');
    });
  }
  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
