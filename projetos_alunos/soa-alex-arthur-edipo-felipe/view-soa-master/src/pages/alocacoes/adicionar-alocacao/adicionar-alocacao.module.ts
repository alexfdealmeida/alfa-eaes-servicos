import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AdicionarAlocacaoPage } from './adicionar-alocacao';

@NgModule({
  declarations: [
    AdicionarAlocacaoPage,
  ],
  imports: [
    IonicPageModule.forChild(AdicionarAlocacaoPage),
  ],
})
export class AdicionarAlocacaoPageModule {}
