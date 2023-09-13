import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarRondaComponent } from './editar-ronda.component';

describe('EditarRondaComponent', () => {
  let component: EditarRondaComponent;
  let fixture: ComponentFixture<EditarRondaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarRondaComponent]
    });
    fixture = TestBed.createComponent(EditarRondaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
