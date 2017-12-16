import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Tarefa } from '../../../models/tarefa';
import { Comentario } from '../../../models/comentario';
import { ComunicacaoComentarioProvider } from '../../../providers/comunicacao-comentario/comunicacao-comentario';
import { DialogoProvider } from '../../../providers/dialogo/dialogo';
import { ComunicacaoAlunoProvider } from '../../../providers/comunicacao-aluno/comunicacao-aluno';
import { Aluno } from '../../../models/aluno';

@IonicPage()
@Component({
  selector: 'page-adicionar-comentarios',
  templateUrl: 'adicionar-comentarios.html',
})
export class AdicionarComentariosPage {

  public tarefa: Tarefa;
  public comentarioTexto: string = '';
  public comentarios: Comentario[] = [];
  public alunos: Aluno[] = [];
  public alunoSelecionado: number;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    private comunicacao: ComunicacaoComentarioProvider,
    private dialogo: DialogoProvider,
    private comunicacaoAluno: ComunicacaoAlunoProvider) {

    this.tarefa = this.navParams.get('Tarefa');

    this.comunicacaoAluno
      .obtenha(false)
      .then(alunos => {

        this.comunicacao
          .obtenhaPelaTarefa(this.tarefa.Id)
          .then(comentarios => {

            this.alunos = alunos;
            this.comentarios = comentarios;
            
            this.comentarios.forEach(comentario => {

              comentario.Aluno = this.alunos.find(a => a.Id == comentario.Aluno.Id);
            });
          });
      });
  }

  comentar() {

    let comentario = {
      Id: 0,
      Aluno: this.alunos.find(a => a.Id == this.alunoSelecionado),
      Data: new Date(),
      Tarefa: this.tarefa,
      Comentario: this.comentarioTexto
    };

    this.comunicacao
      .adicionar(comentario)
      .then(() => {

        this.comentarios.push(comentario);
        this.comentarioTexto = '';
      });
  }

  excluir(comentario: Comentario) {

    this.dialogo
      .exibaAlertaConfirme('Tem certeza que deseja remover o comentário?')
      .then(() => {

        this.comunicacao
          .remover(comentario)
          .then(() => {

            this.comentarios = this.comentarios.filter(c => c.Id != comentario.Id);
          });
      })
      .catch(_ => _);
  }
}
