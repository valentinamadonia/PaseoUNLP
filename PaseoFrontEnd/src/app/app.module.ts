

import { NgModule ,LOCALE_ID} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { UsuarioHomeComponent } from './usuario-home/usuario-home.component';
import { UsuarioService } from './service/usuario.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PpalUserComponent } from './ppal-user/ppal-user.component';
import { RouterModule } from '@angular/router';
import { UsuarioInicioSesionComponent } from './usuario-inicio-sesion/usuario-inicio-sesion.component';
import { UsuarioEditarComponent } from './usuario-editar/usuario-editar.component';
import { PedidoService } from './service/pedido.service';
import { ProductoService } from './service/producto.service';
import { RondaService } from './service/ronda.service';
import { CarritoComponent } from './carrito/carrito.component';
import { ProductoEncargadoService } from './service/producto-encargado.service';
import { NgxPaginationModule } from 'ngx-pagination';
import { UsuarioRegistroComponent } from './usuario-registro/usuario-registro.component';
import { PpalAdminComponent } from './ppal-admin/ppal-admin.component';
import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { ListaPedidosComponent } from './lista-pedidos/lista-pedidos.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ListaRubrosComponent } from './lista-rubros/lista-rubros.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RubroDeProductoService } from './service/rubro-de-producto.service';
import { EditarRubroComponent } from './editar-rubro/editar-rubro.component';
import { HistorialPedidosComponent } from './historial-pedidos/historial-pedidos.component';

import { CommonModule } from '@angular/common';
import { DatePipe } from '@angular/common';
import { ListaProductosComponent } from './lista-productos/lista-productos.component';
import { ProductorService } from './service/productor.service';
import { EditarProductoComponent } from './editar-producto/editar-producto.component';
import { ListaProductoresComponent } from './lista-productores/lista-productores.component';
import { EditarProductorComponent } from './editar-productor/editar-productor.component';
import { ListaRondasComponent } from './lista-rondas/lista-rondas.component';
import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es-AR';
import { EditarRondaComponent } from './editar-ronda/editar-ronda.component';
import { ListaPuntosComponent } from './lista-puntos/lista-puntos.component';
import { PuntoDeRetiroService } from './service/punto-de-retiro.service';
import { CookieService } from "ngx-cookie-service";
import { EditarPuntoComponent } from './editar-punto/editar-punto.component';
import { QuienesSomosComponent } from './quienes-somos/quienes-somos.component';
registerLocaleData(localeEs, 'es-AR');

@NgModule({
  declarations: [
    AppComponent,
    UsuarioHomeComponent,
    PpalUserComponent,
    UsuarioInicioSesionComponent,
    UsuarioEditarComponent,
    CarritoComponent,
    UsuarioRegistroComponent,
    PpalAdminComponent,
    ListaUsuariosComponent,
    ListaPedidosComponent,
    ListaRubrosComponent,
    EditarRubroComponent,
    HistorialPedidosComponent,
    ListaProductosComponent,
    EditarProductoComponent,
    ListaProductoresComponent,
    EditarProductorComponent,
    ListaRondasComponent,
    EditarRondaComponent,
    ListaPuntosComponent,
    EditarPuntoComponent,
    QuienesSomosComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({positionClass: 'toast-bottom-center',timeOut: 10000})
    
  ],
  exports: [ RouterModule ],
  providers: [UsuarioService,ProductoService,PedidoService,ProductoEncargadoService,RubroDeProductoService,ProductorService,RondaService, PuntoDeRetiroService,{ provide: LOCALE_ID, useValue: 'es-AR' },DatePipe,CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
