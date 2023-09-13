import { Component, OnInit } from '@angular/core';
import { ProductorService } from '../service/productor.service';
import { Productor } from 'app/modelos/productor';
import { Estado } from 'app/utils/estado';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UsuarioService } from 'app/service/usuario.service';

@Component({
  selector: 'app-editar-productor',
  templateUrl: './editar-productor.component.html',
  styleUrls: ['./editar-productor.component.css']
})
export class EditarProductorComponent  implements OnInit{

  productor:Productor;

  ngOnInit(): void {
    const state = window.history.state;
    const pWithoutNavigationId = JSON.parse(JSON.stringify(state));
    delete pWithoutNavigationId.navigationId;
    this.productor = pWithoutNavigationId;
    this.estadoSeleccionado=this.productor.estado;
  }

  constructor(private ProductorService:ProductorService,private router: Router,private toastr: ToastrService,private usuarioService:UsuarioService){}

  estadoSeleccionado: Estado;
  estadoProductor = [
    { valor: Estado.ACTIVO, texto: 'Activo' },
    { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];


  onSubmit(p:{nombre:string,descripcion:string}){
      this.productor.estado=this.estadoSeleccionado;
      this.ProductorService.updateProductor(this.productor).subscribe((response)=>{
        this.productor=response;
        this.router.navigate(['listaProductores']);
        this.toastr.success('Productor editado con éxito');
      });
  }

  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
