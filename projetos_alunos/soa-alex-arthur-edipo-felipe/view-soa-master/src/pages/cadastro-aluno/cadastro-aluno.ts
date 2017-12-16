import { Component } from '@angular/core';
import { IonicPage, NavController, ModalController, Events } from 'ionic-angular';
import { Aluno } from '../../models/aluno';
import { DialogoProvider } from '../../providers/dialogo/dialogo';
import { ComunicacaoAlunoProvider } from '../../providers/comunicacao-aluno/comunicacao-aluno';

@IonicPage()
@Component({
  selector: 'page-cadastro-aluno',
  templateUrl: 'cadastro-aluno.html',
})
export class CadastroAlunoPage {

  alunos: Aluno[] = [];

  constructor(
    public navCtrl: NavController,
    public modalCtrl: ModalController,
    private dialogo: DialogoProvider,
    private events: Events,
    private comunicacao: ComunicacaoAlunoProvider) {

    this.events.subscribe('home:adicionarAluno', (aluno: Aluno) => {

      this.comunicacao
        .adicionar(aluno)
        .then(() => {

          this.alunos = this.alunos.filter(a => a.Id != aluno.Id);
          this.alunos.push(aluno);

          this.comunicacao
            .obtenha()
            .then(alunos => {

              this.alunos = alunos;
            });
        })
    });
  }

  ionViewDidEnter() {

    this.comunicacao
      .obtenha()
      .then(alunosresp => {

        this.alunos = alunosresp;
      });
  }

  adicionarAluno() {

    let modal = this.modalCtrl.create('EditarAlunoPage', {}, { cssClass: 'modal-aluno' });

    modal.present();
  }

  editar(aluno: Aluno) {

    let modal = this.modalCtrl.create('EditarAlunoPage', { Aluno: aluno }, { cssClass: 'modal-aluno' });

    modal.present();
  }

  excluir(aluno: Aluno) {

    this.dialogo
      .exibaAlertaConfirme('Tem certeza que deseja remover o aluno?')
      .then(() => {

        this.comunicacao
          .remover(aluno)
          .then(() => {

            this.alunos = this.alunos.filter(a => a.Id != aluno.Id);
          });
      })
      .catch(_ => _);
  }

}
