import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { TabsPage } from '../pages/tabs/tabs';

import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { DialogoProvider } from '../providers/dialogo/dialogo';
import { ComunicacaoAlocacaoProvider } from '../providers/comunicacao-alocacao/comunicacao-alocacao';
import { ComunicacaoComentarioProvider } from '../providers/comunicacao-comentario/comunicacao-comentario';
import { ComunicacaoAlunoProvider } from '../providers/comunicacao-aluno/comunicacao-aluno';
import { ComunicacaoTarefaProvider } from '../providers/comunicacao-tarefa/comunicacao-tarefa';

@NgModule({
  declarations: [
    MyApp,
    TabsPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpModule,
    HttpClientModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    TabsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    { provide: ErrorHandler, useClass: IonicErrorHandler },
    DialogoProvider,
    ComunicacaoAlocacaoProvider,
    ComunicacaoComentarioProvider,
    ComunicacaoAlunoProvider,
    ComunicacaoTarefaProvider
  ]
})
export class AppModule { }
