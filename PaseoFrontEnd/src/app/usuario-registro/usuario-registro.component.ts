import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { AbstractControl, FormGroup, ValidationErrors, ValidatorFn } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';
import { Usuario } from 'app/modelos/usuario';
import { UsuarioService } from 'app/service/usuario.service';
import { Estado } from 'app/utils/estado';
import { Rol } from 'app/utils/rol';
import { ToastrService } from 'ngx-toastr';
import { catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-usuario-registro',
  templateUrl: './usuario-registro.component.html',
  styleUrls: ['./usuario-registro.component.css']
})
export class UsuarioRegistroComponent {

  user: Usuario;


  rolSeleccionado: Rol;
   rolUser = [
    { valor: Rol.USUARIO, texto: 'Usuario' },
    { valor: Rol.ADMINISTRADOR, texto: 'Administrador'}
  ];

  constructor(private usuarioService: UsuarioService, private router: Router,private toastr: ToastrService){

  }

  isPasswordValid(password:string){
     return password.length>=8;
  }

  onSubmit(u: { nombre: string, apellido: string, email: string, telefono: number, direccion: string, password: string,passwordConfirm:string}) {

    if (u.password===u.passwordConfirm && u.password.length>=8) {
      
    this.user = new Usuario(u.nombre, u.apellido, u.email, u.direccion, u.telefono, u.password, [], Rol.USUARIO, Estado.ACTIVO)
    this.usuarioService.createUsuario(this.user).pipe(
      catchError((error) => {
        if (error.status === 409) {
          this.toastr.error('El correo electrónico ya está en uso', 'Error');
        } else {
          this.toastr.error('Se produjo un error al agregar el usuario', 'Error');
        }
        return throwError(error);
      })
    ).subscribe((response: Usuario) => {
      this.user = response;
        const parametros: NavigationExtras = {
          state: this.user
        };
        this.router.navigate(['ppalUser'], parametros);
        this.usuarioService.setUser(this.user);
    });
   }
  }
}
