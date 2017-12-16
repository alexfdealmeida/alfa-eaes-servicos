import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DialogoProvider } from '../dialogo/dialogo';
import { Aluno } from '../../models/aluno';

const urlApi = 'http://localhost:12017/usuarios';

@Injectable()
export class ComunicacaoAlunoProvider {

  constructor(
    private http: HttpClient,
    private dialogo: DialogoProvider) { }

  obtenha(removerLoading: boolean = true) {

    this.dialogo.exibaLoadingPadrao();

    return this.http
      .get(urlApi)
      .toPromise()
      .then((resp: any) => {

        if (removerLoading) this.dialogo.removaLoading();

        return this.mapeieAluno(resp);
      });
  }

  remover(aluno: Aluno) {

    let servico = `${urlApi}/${aluno.Id}`;

    this.dialogo.exibaLoadingPadrao();

    return this.http
      .delete(servico)
      .toPromise()
      .then(resp => {

        this.dialogo.removaLoading();

        return resp;
      });
  }

  adicionar(aluno: Aluno) {

    if (aluno.Id && aluno.Id != 0) {

      return this.atualizar(aluno);

    } else {

      this.dialogo.exibaLoadingPadrao();

      return this.http
        .post(urlApi, {
          id: 0,
          nome: aluno.Nome,
          email: aluno.Curso
        })
        .toPromise()
        .then((resp: any) => {

          this.dialogo.removaLoading();

          return resp;
        })
        .catch(resp => {
          
          if (!resp.error.sucess) {

            resp.error.errors.forEach(erro => {

              this.dialogo.exibaToastAlerta(erro);
            });
          }
        });
    }
  }

  atualizar(aluno: Aluno) {

    this.dialogo.exibaLoadingPadrao();

    return this.http
      .put(urlApi, {
        id: aluno.Id,
        nome: aluno.Nome,
        email: aluno.Curso
      })
      .toPromise()
      .then(resp => {

        this.dialogo.removaLoading();

        return resp;
      });
  }

  mapeieAluno(resposta: any) {

    let alunos: Aluno[] = [];

    resposta.forEach(resp => {

      alunos.push({
        Id: resp.id,
        Nome: resp.nome,
        Curso: resp.email,
        Materia: ''
      })
    });

    return alunos;
  }
}
