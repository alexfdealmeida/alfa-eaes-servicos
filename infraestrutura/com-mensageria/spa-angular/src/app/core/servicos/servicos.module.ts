import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlocacaoDeTarefasService } from './alocacao-de-tarefas.service';
import { TarefaService } from './tarefa.service';
import { UsuarioService } from './usuario.service';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [
    AlocacaoDeTarefasService,
    TarefaService,
    UsuarioService
  ]
})
export class ServicosModule { }
