import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PpalUserComponent } from './ppal-user/ppal-user.component';
import { UsuarioHomeComponent } from './usuario-home/usuario-home.component';
import { UsuarioEditarComponent } from './usuario-editar/usuario-editar.component';
import { UsuarioInicioSesionComponent } from './usuario-inicio-sesion/usuario-inicio-sesion.component';
import { CarritoComponent } from './carrito/carrito.component';
import { UsuarioRegistroComponent } from './usuario-registro/usuario-registro.component';
import { PpalAdminComponent } from './ppal-admin/ppal-admin.component';
import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { ListaPedidosComponent } from './lista-pedidos/lista-pedidos.component';
import { ListaRubrosComponent } from './lista-rubros/lista-rubros.component';
import { EditarRubroComponent } from './editar-rubro/editar-rubro.component';
import { HistorialPedidosComponent } from './historial-pedidos/historial-pedidos.component';
import { ListaProductosComponent } from './lista-productos/lista-productos.component';
import { EditarProductoComponent } from './editar-producto/editar-producto.component';
import { ListaProductoresComponent } from './lista-productores/lista-productores.component';
import { EditarProductorComponent } from './editar-productor/editar-productor.component';
import { ListaRondasComponent } from './lista-rondas/lista-rondas.component';
import { EditarRondaComponent } from './editar-ronda/editar-ronda.component';
import { ListaPuntosComponent } from './lista-puntos/lista-puntos.component';
import { EditarPuntoComponent } from './editar-punto/editar-punto.component';
import { QuienesSomosComponent } from './quienes-somos/quienes-somos.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full' },
  {path: 'home', component: UsuarioHomeComponent},
  {path: 'registro', component: UsuarioRegistroComponent},
  {path: "login",component: UsuarioInicioSesionComponent},
  {path: 'ppalUser', component: PpalUserComponent},
  {path: 'ppalAdmin', component: PpalAdminComponent},
  {path: 'listaUser',component: ListaUsuariosComponent},
  {path: 'listaPedidos', component: ListaPedidosComponent},
  {path: 'listaRubros', component: ListaRubrosComponent},
  {path: 'editarRubro', component: EditarRubroComponent},
  {path: 'editarUser', component: UsuarioEditarComponent},
  {path: 'carrito',component: CarritoComponent},
  {path: 'historialPedidos',component: HistorialPedidosComponent},
  {path: 'listaProductos', component: ListaProductosComponent},
  {path: 'editarProducto', component: EditarProductoComponent},
  {path: 'listaProductores', component: ListaProductoresComponent},
  {path: 'editarProductor',component: EditarProductorComponent},
  {path: 'listaRondas', component: ListaRondasComponent},
  {path: 'editarRonda', component: EditarRondaComponent},
  {path: 'listaPuntos', component: ListaPuntosComponent},
  {path: 'editarPunto', component: EditarPuntoComponent},
  {path: 'quienesSomos', component: QuienesSomosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
