import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule, LOCALE_ID } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { registerLocaleData } from '@angular/common';

import localePTBR from '@angular/common/locales/pt';
registerLocaleData(localePTBR);

import { MaterialModule } from './material/material.module';
import { ServicosModule } from './core/servicos/servicos.module';

import { AppComponent } from './app.component';
import { UsuariosComponent } from './componentes/usuarios/usuarios.component';
import { TarefasComponent } from './componentes/tarefas/tarefas.component';
import { AlocacaoComponent } from './componentes/alocacao/alocacao.component';



@NgModule({
  declarations: [
    AppComponent,
    UsuariosComponent,
    TarefasComponent,
    AlocacaoComponent
  ],
  imports: [
    // módulos do angular
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,

    // meus módulos
    MaterialModule,
    ServicosModule
  ],
  providers: [ { provide: LOCALE_ID, useValue: 'pt' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
