import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { EditarAlunoPage } from './editar-aluno';

@NgModule({
  declarations: [
    EditarAlunoPage,
  ],
  imports: [
    IonicPageModule.forChild(EditarAlunoPage),
  ],
})
export class EditarAlunoPageModule {}
