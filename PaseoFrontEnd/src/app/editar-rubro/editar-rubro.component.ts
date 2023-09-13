import { Component,OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { RubroDeProducto } from 'app/modelos/rubro-de-producto';
import { RubroDeProductoService } from 'app/service/rubro-de-producto.service';
import { UsuarioService } from 'app/service/usuario.service';
import { Estado } from 'app/utils/estado';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-editar-rubro',
  templateUrl: './editar-rubro.component.html',
  styleUrls: ['./editar-rubro.component.css']
})
export class EditarRubroComponent implements OnInit{

  rubro: RubroDeProducto;
  rubros:RubroDeProducto[]=[];
  estadoSeleccionado: Estado;
  estadoRubro = [
    { valor: Estado.ACTIVO, texto: 'Activo' },
    { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];



  ngOnInit(): void {
    const state = window.history.state;
    const userWithoutNavigationId = JSON.parse(JSON.stringify(state));
    delete userWithoutNavigationId.navigationId;
    this.rubro = userWithoutNavigationId;
    this.estadoSeleccionado=this.rubro.estado;
  }

  constructor(private rubroService:RubroDeProductoService,private router: Router,private toastr: ToastrService,private usuarioService:UsuarioService){}

  onSubmit(){
    this.rubro.estado=this.estadoSeleccionado;
    this.rubroService.updateRubro(this.rubro).subscribe((response)=>{
    this.rubro=response;
    this.router.navigate(['listaRubros']);
    this.toastr.success('Rubro editado con éxito');
    });
  }

  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
