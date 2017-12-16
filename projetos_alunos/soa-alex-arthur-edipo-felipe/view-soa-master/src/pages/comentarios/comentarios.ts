import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ModalController } from 'ionic-angular';
import { Alocacoes } from '../../models/alocacoes';
import { DialogoProvider } from '../../providers/dialogo/dialogo';
import { Tarefa } from '../../models/tarefa';
import { ComunicacaoComentarioProvider } from '../../providers/comunicacao-comentario/comunicacao-comentario';
import { Comentario } from '../../models/comentario';
import { ComunicacaoTarefaProvider } from '../../providers/comunicacao-tarefa/comunicacao-tarefa';

@IonicPage()
@Component({
  selector: 'page-comentarios',
  templateUrl: 'comentarios.html',
})
export class ComentariosPage {

  comentarios: Comentario[] = [];

  tarefas: Tarefa[] = [];

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public modalCtrl: ModalController,
    private dialogo: DialogoProvider,
    private comunicacao: ComunicacaoComentarioProvider,
    private comunicacaoTarefa: ComunicacaoTarefaProvider) {
  }

  ionViewDidEnter() {

    this.comunicacaoTarefa
      .obtenha(false)
      .then(tarefas => {

        this.comunicacao
          .obtenha()
          .then(comentarios => {

            this.comentarios = comentarios;
            this.tarefas = tarefas;
          });
      });
  }

  comentar(tarefa: Tarefa) {

    this.navCtrl.push('AdicionarComentariosPage', { Tarefa: tarefa });
  }

  obtenhaQuantidadeDeComentariosDaTarefa(tarefa: Tarefa) {

    return this.comentarios.filter(c => c.Tarefa.Id == tarefa.Id).length;
  }
}
