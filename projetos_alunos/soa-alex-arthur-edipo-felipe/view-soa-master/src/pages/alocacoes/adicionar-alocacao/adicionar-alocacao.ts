import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController } from 'ionic-angular';
import { DialogoProvider } from '../../../providers/dialogo/dialogo';
import { Events } from 'ionic-angular/util/events';
import { Alocacoes } from '../../../models/alocacoes';
import { Aluno } from '../../../models/aluno';
import { Tarefa } from '../../../models/tarefa';
import { applySourceSpanToStatementIfNeeded } from '@angular/compiler/src/output/output_ast';
import { ComunicacaoAlunoProvider } from '../../../providers/comunicacao-aluno/comunicacao-aluno';
import { ComunicacaoTarefaProvider } from '../../../providers/comunicacao-tarefa/comunicacao-tarefa';

@IonicPage()
@Component({
  selector: 'page-adicionar-alocacao',
  templateUrl: 'adicionar-alocacao.html',
})
export class AdicionarAlocacaoPage {

  alunos: Aluno[] = [];
  tarefas: Tarefa[] = [];
  alunoSelecionado: number;
  tarefaSelecionada: number;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public viewController: ViewController,
    public dialogo: DialogoProvider,
    public events: Events,
    private comunicacaoAluno: ComunicacaoAlunoProvider,
    private comunicacaoTarefa: ComunicacaoTarefaProvider) {

    this.comunicacaoAluno
      .obtenha(false)
      .then(alunos => {

        this.alunos = alunos;

        this.comunicacaoTarefa
          .obtenha()
          .then(tarefas => {

            this.tarefas = tarefas;
          });
      });
  }

  cancelar() {

    this.viewController.dismiss();
  }

  gravar() {

    if (!this.alunoSelecionado) {

      this.dialogo.exibaToastAlerta('Informe o aluno!');

      return;
    }

    if (!this.tarefaSelecionada) {

      this.dialogo.exibaToastAlerta('Informe a tarefa!');

      return;
    }

    this.events.publish('home:adicionarAlocacao', this.criarAlocacao());

    this.viewController.dismiss();
  }

  private criarAlocacao(): Alocacoes {

    return {
      Id: '',
      Aluno: this.alunos.find(a => a.Id == this.alunoSelecionado),
      Tarefa: this.tarefas.find(t => t.Id == this.tarefaSelecionada)
    };
  }

}
