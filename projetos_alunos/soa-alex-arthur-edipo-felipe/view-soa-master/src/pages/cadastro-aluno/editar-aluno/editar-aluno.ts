import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ViewController } from 'ionic-angular/navigation/view-controller';
import { Input } from '@angular/core/src/metadata/directives';
import { Events } from 'ionic-angular/util/events';
import { Aluno } from '../../../models/aluno';
import { DialogoProvider } from '../../../providers/dialogo/dialogo';

@IonicPage()
@Component({
  selector: 'page-editar-aluno',
  templateUrl: 'editar-aluno.html',
})
export class EditarAlunoPage {

  aluno: Aluno = new Aluno();

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public viewController: ViewController,
    public dialogo: DialogoProvider,
    public events: Events) {

    this.aluno = this.navParams.get('Aluno');

    if (!this.aluno) this.aluno = new Aluno();
  }

  cancelar() {

    this.viewController.dismiss();
  }

  gravar() {

    if (!this.aluno.Nome) {

      this.dialogo.exibaToastAlerta('Informe o nome do aluno!');

      return;
    }

    this.events.publish('home:adicionarAluno', this.aluno);

    this.viewController.dismiss();
  }

}
