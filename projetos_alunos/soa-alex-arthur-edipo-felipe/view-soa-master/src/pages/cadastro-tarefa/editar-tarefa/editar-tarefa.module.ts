import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { EditarTarefaPage } from './editar-tarefa';

@NgModule({
  declarations: [
    EditarTarefaPage,
  ],
  imports: [
    IonicPageModule.forChild(EditarTarefaPage),
  ],
})
export class EditarTarefaPageModule {}
