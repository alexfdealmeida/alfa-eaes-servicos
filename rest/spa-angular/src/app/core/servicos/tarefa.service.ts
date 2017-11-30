import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { TarefaEntityTo } from '../model/to/to';

import { URL } from '../model/constantes/constantes';
import { PaginaDeRespostaDoSpring } from '../model/to/pagina-de-resposta-do-spring';

@Injectable()
export class TarefaService {

  constructor(private http: HttpClient) { }

  findAll(sort: string, order: string, page: number)
    : Observable<PaginaDeRespostaDoSpring<TarefaEntityTo>> {

    const requestUrl = `${URL.baseUrl}${URL.tarefasAll_Get}`;

    return this.http.get<PaginaDeRespostaDoSpring<TarefaEntityTo>>(requestUrl, {
      params: new HttpParams()
        .set('sort', sort + ',' + order)
        .set('page', '' + page)
    });
  }

  saveOrCreate(tarefa: TarefaEntityTo): Observable<TarefaEntityTo> {

    const requestUrl = `${URL.baseUrl}${URL.tarefasSaveOrCreate_Post}`;
    return this.http.post<TarefaEntityTo>(requestUrl, tarefa);
  }

  remove(id: number): Observable<void> {

    const requestUrl = `${URL.baseUrl}${URL.tarefasRemove_Delete}`
      .replace(URL.tarefasRemove_Delete_Par1, '' + id);

    return this.http.delete<void>(requestUrl);
  }

}
