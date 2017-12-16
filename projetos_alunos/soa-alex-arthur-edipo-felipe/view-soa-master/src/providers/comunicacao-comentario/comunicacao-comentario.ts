import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import { Alocacoes } from '../../models/alocacoes';
import { DialogoProvider } from '../dialogo/dialogo';
import { Comentario } from '../../models/comentario';

const urlApi = 'http://localhost:9000/comentarios/';

@Injectable()
export class ComunicacaoComentarioProvider {

  constructor(
    private http: HttpClient,
    private dialogo: DialogoProvider) { }

  obtenha() {

    this.dialogo.exibaLoadingPadrao();

    let servico = urlApi + 'all';

    return this.http
      .get(servico)
      .toPromise()
      .then((resp: any) => {

        this.dialogo.removaLoading();

        return this.mapeieComentarios(resp.content);
      });
  }

  obtenhaPelaTarefa(idTarefa: number) {

    return this.obtenha()
      .then(comentarios => {

        return comentarios.filter(c => c.Tarefa.Id == idTarefa);
      });
  }

  remover(comentario: Comentario) {

    let servico = `${urlApi}remove/${comentario.Id}`;

    this.dialogo.exibaLoadingPadrao();

    return this.http
      .delete(servico)
      .toPromise()
      .then(resp => {

        this.dialogo.removaLoading();

        return resp;
      });
  }

  adicionar(comentario: Comentario) {

    debugger;

    let servico = urlApi + 'save-or-create';

    this.dialogo.exibaLoadingPadrao();

    return this.http
      .post(servico, {
        id: 0,
        corpo: comentario.Comentario,
        data: comentario.Data.getTime(),
        autor: {
          id: comentario.Aluno.Id,
          nome: '',
          email: ''
        },
        tarefa: {
          id: comentario.Tarefa.Id,
          titulo: '',
          descricao: '',
          inicio: '',
          fim: ''
        }
      })
      .toPromise()
      .then((resp: any) => {

        comentario.Id = resp.id;
        this.dialogo.removaLoading();

        return resp;
      });
  }

  private mapeieComentarios(resposta: any): Comentario[] {

    let comentarios: Comentario[] = [];

    resposta.forEach(coment => {
      comentarios.push({
        Id: coment.id,
        Comentario: coment.corpo,
        Data: new Date(coment.data),
        Aluno: {
          Id: coment.autor.id,
          Nome: coment.autor.nome,
          Materia: coment.autor.email,
          Curso: ''
        },
        Tarefa: {
          Id: coment.tarefa.id,
          Titulo: coment.tarefa.titulo,
          Descricao: coment.tarefa.descricao,
          Inicio: new Date(coment.tarefa.dataInicio),
          Fim: new Date(coment.tarefa.dataFim)
        }
      })
    });

    return comentarios;
  }
}