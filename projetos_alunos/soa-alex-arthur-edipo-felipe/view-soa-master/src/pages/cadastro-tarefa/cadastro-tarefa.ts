import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ModalController } from 'ionic-angular';
import { Tarefa } from '../../models/tarefa';
import { DialogoProvider } from '../../providers/dialogo/dialogo';
import { Events } from 'ionic-angular/util/events';
import { ComunicacaoTarefaProvider } from '../../providers/comunicacao-tarefa/comunicacao-tarefa';

@IonicPage()
@Component({
  selector: 'page-cadastro-tarefa',
  templateUrl: 'cadastro-tarefa.html',
})
export class CadastroTarefaPage {

  tarefas: Tarefa[] = [];

  constructor(
    public navCtrl: NavController,
    public modalCtrl: ModalController,
    private dialogo: DialogoProvider,
    private events: Events,
    private comunicacao: ComunicacaoTarefaProvider) {

    this.events.subscribe('home:adicionarTarefa', (tarefa: Tarefa) => {

      debugger;

      this.comunicacao
        .adicionar(tarefa)
        .then(() => {

          this.tarefas = this.tarefas.filter(a => a.Id != tarefa.Id);
          this.tarefas.push(tarefa);

          this.comunicacao
            .obtenha()
            .then(tarefas => {

              this.tarefas = tarefas;
            });
        });
    });
  }

  ionViewDidEnter() {

    this.comunicacao
      .obtenha()
      .then(tarefas => {

        this.tarefas = tarefas;
      });
  }

  adicionarTarefa() {

    let modal = this.modalCtrl.create('EditarTarefaPage', {}, { cssClass: 'modal-tarefa' });

    modal.present();
  }

  editar(tarefa: Tarefa) {

    let modal = this.modalCtrl.create('EditarTarefaPage', { Tarefa: tarefa }, { cssClass: 'modal-tarefa' });

    modal.present();
  }

  excluir(tarefa: Tarefa) {

    this.dialogo
      .exibaAlertaConfirme('Tem certeza que deseja remover a tarefa?')
      .then(() => {

        this.comunicacao
          .remover(tarefa)
          .then(() => {

            this.tarefas = this.tarefas.filter(t => t.Id != tarefa.Id);
          });
      })
      .catch(_ => _);
  }

}
