import { TestBed, inject } from '@angular/core/testing';

import { AlocacaoDeTarefasService } from './alocacao-de-tarefas.service';

describe('AlocacaoDeTarefasService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AlocacaoDeTarefasService]
    });
  });

  it('should be created', inject([AlocacaoDeTarefasService], (service: AlocacaoDeTarefasService) => {
    expect(service).toBeTruthy();
  }));
});
