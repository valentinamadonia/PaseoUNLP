import { Component } from '@angular/core';
import { Usuario } from '../modelos/usuario';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { UsuarioService } from '../service/usuario.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-usuario-editar',
  templateUrl: './usuario-editar.component.html',
  styleUrls: ['./usuario-editar.component.css']
})
export class UsuarioEditarComponent {
  user: Usuario;

  user$: Observable<Usuario>;


  constructor(private route: ActivatedRoute,private router: Router, private usuarioService:UsuarioService){
    const usuarioGuardado = this.usuarioService.getUser();
    if (usuarioGuardado) {
      console.log('Usuario guardado:', usuarioGuardado);
      this.user=usuarioGuardado;
    }
  }
 
  ngOnInit():void {
    
  }

  onSubmit(u:{nombre:string,apellido:string,email:string,telefono:number,direccion:string,password:string}){
    
        this.user.nombre=u.nombre;
        this.user.apellido=u.apellido;
        this.user.email=u.email;
        this.user.telefono=u.telefono;
        this.user.direccion=u.direccion;
        this.user.password=u.password;
        console.log('luego de editar',this.user);
        this.usuarioService.updateUsuario(this.user).subscribe((response: Usuario) => {
          this.user=response;
        },(error: HttpErrorResponse) => {
          // Error en la solicitud
          console.log('Error en la solicitud de actualización:');
          console.log('Status:', error.status);
          console.log('Mensaje:', error.message);
          console.log('Cuerpo de error:', error.error);
        }
        );
        const parametros: NavigationExtras = {
          state: this.user
        };
        if(this.user.rol==='USUARIO'){
          this.router.navigate(['ppalUser'],parametros);
        }else{
          this.router.navigate(['ppalAdmin'],parametros);
        }
        this.usuarioService.setUser(this.user);
  }


  pagPrincipal(){
    const parametros: NavigationExtras = {
      state: this.user
    };
    this.router.navigate(['ppalUser'],parametros);
  }
}