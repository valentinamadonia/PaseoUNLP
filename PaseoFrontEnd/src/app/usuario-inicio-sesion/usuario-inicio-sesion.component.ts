import { Component, OnInit } from '@angular/core';
import { Usuario } from '../modelos/usuario';
import { UsuarioService } from '../service/usuario.service';
import { catchError, throwError } from 'rxjs';
import { NavigationExtras, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Rol } from 'app/utils/rol';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-usuario-inicio-sesion',
  templateUrl: './usuario-inicio-sesion.component.html',
  styleUrls: ['./usuario-inicio-sesion.component.css']
})
export class UsuarioInicioSesionComponent implements OnInit{
  user: Usuario;
  userForm: FormGroup;

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      password: ['', Validators.required],
      email: ['', Validators.required]
      // Otros campos del formulario
    });
  }


  constructor(private usuarioService:UsuarioService,private router: Router,private toastr: ToastrService,private formBuilder: FormBuilder){}


  onSubmit(u:{email:string,password:string}){
    this.usuarioService.getUsuarioEmail(u.email).pipe(
      catchError((error) => {
        if (error.status === 409) {
          this.toastr.error('El email no se encuentra registrado', 'Error');
        } else {
          this.toastr.error('El email no se encuentra registrado', 'Error');
        }
        return throwError(error);
      })  
    ).subscribe((response)=>{
      this.user=response;
      if(this.user.password===u.password){
        if(this.user.rol===Rol.USUARIO){
          const parametros: NavigationExtras = {
            state: this.user
          };
          this.router.navigate(['ppalUser'], parametros);
        } else{
          const parametros: NavigationExtras = {
            state: this.user
          };
          this.router.navigate(['ppalAdmin'], parametros);
        }
        this.usuarioService.setUser(this.user);
      }else{
        this.toastr.error('La contraseña es incorrecta', 'Error');
      }
    });
  }
}
