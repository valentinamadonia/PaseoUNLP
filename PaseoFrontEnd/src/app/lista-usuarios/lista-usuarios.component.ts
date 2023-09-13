import { Component } from '@angular/core';
import { Usuario } from 'app/modelos/usuario';
import { UsuarioService } from 'app/service/usuario.service';
import { Estado } from 'app/utils/estado';
import { Rol } from 'app/utils/rol';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.css']
})
export class ListaUsuariosComponent {

  public user:Usuario;


  usuarios:Usuario[]=[];

  ngOnInit():void {
    //uso de cookie
    if(this.usuarioService.isUserLoggedIn()){
      const usuarioGuardado = this.usuarioService.getUser();
      if (usuarioGuardado) {
        this.user=usuarioGuardado;
        console.log('Usuario guardado:', usuarioGuardado);
      }
    }
    //

    this.obtenerUsuarios();

  }

  obtenerUsuarios(){
    this.usuarioService.getUsuarios().subscribe((users)=>{
      this.usuarios=users;
      const index = this.usuarios.findIndex(u => u.id === this.user.id);
      //me elimino de la lista
      if (index !== -1) {
        this.usuarios.splice(index, 1);
      }
    });
  }

  constructor(private usuarioService:UsuarioService, private router: Router,private toastr: ToastrService){

  }

  activarDesactivar(user:Usuario){
    if(user.estado===Estado.ACTIVO){
      user.estado=Estado.SUSPENDIDO;
    }else{
      user.estado=Estado.ACTIVO;
    }
    this.usuarioService.updateUsuario(user).subscribe();
  }

  usuarioAdmin(user:Usuario){
     if(user.rol===Rol.ADMINISTRADOR){
          user.rol=Rol.USUARIO;
     }else{
          user.rol=Rol.ADMINISTRADOR;
     }
     this.usuarioService.updateUsuario(user).subscribe();
  }

  userDisplay = "none";
  openModalUser() {
    this.userDisplay = "block";
  }
  onCloseHandledUser() {
    this.userDisplay = "none";
  }


  rolSeleccionado: Rol;
  rolUser = [
   { valor: Rol.USUARIO, texto: 'Usuario' },
   { valor: Rol.ADMINISTRADOR, texto: 'Administrador'}
  ];

  onSubmit(u: { nombre: string, apellido: string, email: string, telefono: number, direccion: string, password: string }){
     const userNuevo=new Usuario(u.nombre, u.apellido, u.email, u.direccion, u.telefono, u.password, [], this.rolSeleccionado, Estado.ACTIVO)
     this.usuarioService.createUsuario(userNuevo).subscribe((response: Usuario) => {
      this.toastr.success('Usuario agregado correctamente', 'Éxito');
      this.router.navigate(['listaUser']);
      this.onCloseHandledUser();
      this.obtenerUsuarios();
     });
  }

  cerrarSesion(){
    this.usuarioService.clearUser();
    this.router.navigate(['home']);
  }
}
