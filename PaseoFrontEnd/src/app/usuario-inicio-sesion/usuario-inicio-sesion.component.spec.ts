import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioInicioSesionComponent } from './usuario-inicio-sesion.component';

describe('UsuarioInicioSesionComponent', () => {
  let component: UsuarioInicioSesionComponent;
  let fixture: ComponentFixture<UsuarioInicioSesionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UsuarioInicioSesionComponent]
    });
    fixture = TestBed.createComponent(UsuarioInicioSesionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
