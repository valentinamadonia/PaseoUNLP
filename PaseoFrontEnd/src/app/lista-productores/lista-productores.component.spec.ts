import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaProductoresComponent } from './lista-productores.component';

describe('ListaProductoresComponent', () => {
  let component: ListaProductoresComponent;
  let fixture: ComponentFixture<ListaProductoresComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListaProductoresComponent]
    });
    fixture = TestBed.createComponent(ListaProductoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
