import { Component } from '@angular/core';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = 'CadastroAlunoPage';
  tab2Root = 'CadastroTarefaPage';
  tab3Root = 'AlocacoesPage';
  tab4Root = 'ComentariosPage';

  constructor() {

  }
}
