import { TestBed, inject } from '@angular/core/testing';

import { UsuarioWsService } from './usuario-ws.service';

describe('UsuarioWsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UsuarioWsService]
    });
  });

  it('should be created', inject([UsuarioWsService], (service: UsuarioWsService) => {
    expect(service).toBeTruthy();
  }));
});
