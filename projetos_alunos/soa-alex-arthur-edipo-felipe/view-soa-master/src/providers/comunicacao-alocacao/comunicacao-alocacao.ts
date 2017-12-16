import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import { Alocacoes } from '../../models/alocacoes';
import { DialogoProvider } from '../dialogo/dialogo';

const urlApi = 'http://localhost:3000/alocacoes';

@Injectable()
export class ComunicacaoAlocacaoProvider {

  constructor(
    private http: HttpClient,
    private dialogo: DialogoProvider) { }

  obtenhaAlocacoes() {

    this.dialogo.exibaLoadingPadrao();

    return this.http
      .get(urlApi)
      .toPromise()
      .then(resp => {

        return resp;
      });
  }

  remover(alocacao: Alocacoes) {

    this.dialogo.exibaLoadingPadrao();

    return this.http
      .delete(`${urlApi}/${alocacao.Id}`)
      .toPromise()
      .then(resp => {

        this.dialogo.removaLoading();

        return resp;
      });
  }

  adicionar(alocacao: Alocacoes) {

    this.dialogo.exibaLoadingPadrao();

    return this.http
      .post(urlApi, {
        tarefa: alocacao.Tarefa.Id,
        aluno: alocacao.Aluno.Id
      })
      .toPromise()
      .then(resp => {

        this.dialogo.removaLoading();

        return resp;
      });
  }
}
