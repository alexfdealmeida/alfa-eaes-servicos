import { TestBed, inject } from '@angular/core/testing';

import { TarefaService } from './tarefa.service';

describe('TarefaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TarefaService]
    });
  });

  it('should be created', inject([TarefaService], (service: TarefaService) => {
    expect(service).toBeTruthy();
  }));
});
