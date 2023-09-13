import { TestBed } from '@angular/core/testing';

import { PuntoDeRetiroService } from './punto-de-retiro.service';

describe('PuntoDeRetiroService', () => {
  let service: PuntoDeRetiroService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PuntoDeRetiroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
