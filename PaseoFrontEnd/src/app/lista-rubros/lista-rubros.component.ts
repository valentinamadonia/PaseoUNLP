import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Producto } from 'app/modelos/producto';
import { RubroDeProducto } from 'app/modelos/rubro-de-producto';
import { Usuario } from 'app/modelos/usuario';
import { RubroDeProductoService } from 'app/service/rubro-de-producto.service';
import { UsuarioService } from 'app/service/usuario.service';
import { Estado } from 'app/utils/estado';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-lista-rubros',
  templateUrl: './lista-rubros.component.html',
  styleUrls: ['./lista-rubros.component.css']
})
export class ListaRubrosComponent  implements OnInit{
   
  user:Usuario;
  rubros:RubroDeProducto[]=[];

  constructor(private rubroService:RubroDeProductoService,private route: ActivatedRoute,private router: Router,private toastr: ToastrService,private usuarioService:UsuarioService){}

  ngOnInit(): void {
    
    const state = window.history.state;
    const userWithoutNavigationId = JSON.parse(JSON.stringify(state));
    delete userWithoutNavigationId.navigationId;
    this.user = userWithoutNavigationId;
    
    this.obtenerRubros();
     
  }

  obtenerRubros(){
    this.rubroService.getRubros().subscribe((response)=>{
      this.rubros=response;
    });
  }

  display = "none";
  openModal() {
    this.display = "block";
  }
  onCloseHandled() {
    this.display = "none";
  }

  cambiarEstadoRubro(r:RubroDeProducto){
     this.rubroService.updateRubro(r).subscribe((response)=>{
     });
  }

  estadoSeleccionado: Estado;
  estadoRubro = [
   { valor: Estado.ACTIVO, texto: 'Activo' },
   { valor: Estado.SUSPENDIDO, texto: 'Suspendido'}
  ];

  onSubmit(r:{nombre:string}){
    let rubroNuevo=new RubroDeProducto(r.nombre,this.estadoSeleccionado,[]);
    this.rubroService.createRubro(rubroNuevo).subscribe((response)=>{
      console.log(response);
      this.toastr.success('Rubro agregado correctamente', 'Éxito');
      this.router.navigate(['listaRubros']);
      this.onCloseHandled();
      this.obtenerRubros();
    });
  }

  Editar(rubro:RubroDeProducto){
    const parametros: NavigationExtras = {
      state: rubro
    };
    this.router.navigate(['editarRubro'],parametros);
  }
  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}

