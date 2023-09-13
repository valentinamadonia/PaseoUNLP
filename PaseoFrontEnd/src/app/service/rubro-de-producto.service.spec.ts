import { TestBed } from '@angular/core/testing';

import { RubroDeProductoService } from './rubro-de-producto.service';

describe('RubroDeProductoService', () => {
  let service: RubroDeProductoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RubroDeProductoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
