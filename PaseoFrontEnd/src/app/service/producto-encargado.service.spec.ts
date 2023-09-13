import { TestBed } from '@angular/core/testing';

import { ProductoEncargadoService } from './producto-encargado.service';

describe('ProductoEncargadoService', () => {
  let service: ProductoEncargadoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductoEncargadoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
