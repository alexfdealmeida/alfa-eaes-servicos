import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController } from 'ionic-angular';
import { DialogoProvider } from '../../../providers/dialogo/dialogo';
import { Tarefa } from '../../../models/tarefa';
import { Events } from 'ionic-angular/util/events';

@IonicPage()
@Component({
  selector: 'page-editar-tarefa',
  templateUrl: 'editar-tarefa.html',
})
export class EditarTarefaPage {

  tarefa: Tarefa;

  inicio: string = '16/12/2017';
  fim: string = '16/12/2017';

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public viewController: ViewController,
    public dialogo: DialogoProvider,
    public events: Events) {

    this.tarefa = this.navParams.get('Tarefa');

    if (!this.tarefa) {
      this.tarefa = new Tarefa();
    } else {

      this.inicio = this.formateData(this.tarefa.Inicio);
      this.fim = this.formateData(this.tarefa.Fim);
    }
  }

  cancelar() {

    this.viewController.dismiss();
  }

  gravar() {

    if (!this.tarefa.Titulo) {

      this.dialogo.exibaToastAlerta('Informe o título da tarefa!');

      return;
    }

    this.tarefa.Inicio = this.formate(this.inicio);
    
    if (this.fim)
      this.tarefa.Fim = this.formate(this.fim);

    this.events.publish('home:adicionarTarefa', this.tarefa);

    this.viewController.dismiss();
  }

  private formate(dataStr: string) {

    let valores = dataStr.split('/');

    let ano = new Number(valores[2]);
    let mes = new Number(valores[1]);
    let dia = new Number(valores[0]);

    return new Date(ano as number, mes as number, dia as number, 0, 0, 0);
  }

  private formateData(data: Date) {

    let dia = this.formateNumero(data.getDate(), 2);
    let mes = this.formateNumero(data.getMonth() + 1, 2);
    let ano = data.getFullYear();

    return `${dia}/${mes}/${ano}`;
  }

  private formateNumero(numero: number, tamanho: number) {

    let valorRetorno = "" + numero;

    while (valorRetorno.length < tamanho) {

      valorRetorno = "0" + valorRetorno;
    }

    return valorRetorno;
  }
}
