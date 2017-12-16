import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AdicionarComentariosPage } from './adicionar-comentarios';
import { PipesModule } from '../../../pipes/pipes.module';

@NgModule({
  declarations: [
    AdicionarComentariosPage,
  ],
  imports: [
    IonicPageModule.forChild(AdicionarComentariosPage),
    PipesModule
  ],
})
export class AdicionarComentariosPageModule { }
