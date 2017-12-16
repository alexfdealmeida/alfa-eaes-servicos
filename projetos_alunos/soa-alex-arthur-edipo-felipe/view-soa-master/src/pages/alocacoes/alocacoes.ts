import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ModalController, Events } from 'ionic-angular';
import { Alocacoes } from '../../models/alocacoes';
import { DialogoProvider } from '../../providers/dialogo/dialogo';
import { Aluno } from '../../models/aluno';
import { Tarefa } from '../../models/tarefa';
import { ComunicacaoAlocacaoProvider } from '../../providers/comunicacao-alocacao/comunicacao-alocacao';
import { ComunicacaoAlunoProvider } from '../../providers/comunicacao-aluno/comunicacao-aluno';
import { ComunicacaoTarefaProvider } from '../../providers/comunicacao-tarefa/comunicacao-tarefa';

@IonicPage()
@Component({
  selector: 'page-alocacoes',
  templateUrl: 'alocacoes.html',
})
export class AlocacoesPage {

  alunos: Aluno[] = [];
  tarefas: Tarefa[] = [];
  alocacoes: Alocacoes[] = [];

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public modalCtrl: ModalController,
    private dialogo: DialogoProvider,
    private events: Events,
    private comunicacao: ComunicacaoAlocacaoProvider,
    private comunicacaoAluno: ComunicacaoAlunoProvider,
    private comunicacaoTarefa: ComunicacaoTarefaProvider) {

    this.crieEventoParaAdicionarAlocacao();
  }

  ionViewDidEnter() {

    this.carregarAlocacoes();
  }

  adicionarAlocacao() {

    let modal = this.modalCtrl.create('AdicionarAlocacaoPage', {}, { cssClass: 'modal-alocacoes' });

    modal.present();
  }

  excluir(alocacao: Alocacoes) {

    this.dialogo
      .exibaAlertaConfirme('Tem certeza que deseja remover a alocação?')
      .then(() => {

        this.comunicacao
          .remover(alocacao)
          .then(() => {

            this.alocacoes = this.alocacoes.filter(a => a.Id != alocacao.Id);
          });
      })
      .catch(_ => _);
  }

  private carregarAlocacoes() {

    this.alocacoes = [];

    this.comunicacao
      .obtenhaAlocacoes()
      .then((alocacoes: any) => {

       this.comunicacaoAluno
          .obtenha(false)
          .then(alunos => {

            this.alunos = alunos;

            this.comunicacaoTarefa
              .obtenha()
              .then(tarefas => {

                this.tarefas = tarefas;

                alocacoes.forEach(alocacao => {

                  this.alocacoes.push(
                    {
                      Id: alocacao._id,
                      Aluno: this.alunos.find(a => a.Id == alocacao.id_aluno),
                      Tarefa: this.tarefas.find(t => t.Id == alocacao.id_tarefa)
                    });
                });
              });
          });
      });
  }

  private crieEventoParaAdicionarAlocacao() {

    this.events.subscribe('home:adicionarAlocacao', (alocacao: Alocacoes) => {

      this.comunicacao
        .adicionar(alocacao)
        .then((resp: any) => {

          this.alocacoes = this.alocacoes.filter(a => a.Id != alocacao.Id);

          alocacao.Id = resp._id;

          this.alocacoes.push(alocacao);
        });
    });
  }
}
