import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { CadastroTarefaPage } from './cadastro-tarefa';

@NgModule({
  declarations: [
    CadastroTarefaPage,
  ],
  imports: [
    IonicPageModule.forChild(CadastroTarefaPage),
  ],
})
export class CadastroTarefaPageModule {}
