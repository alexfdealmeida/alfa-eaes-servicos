import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { UsuarioTarefaEntityTo } from '../model/to/to';

import { URL } from '../model/constantes/constantes';
import { PaginaDeRespostaDoSpring } from '../model/to/pagina-de-resposta-do-spring';

@Injectable()
export class AlocacaoDeTarefasService {

  constructor(private http: HttpClient) { }

  findByTarefaId(tarefaId: number, sort: string, order: string, page: number, pageSize: number)
    : Observable<PaginaDeRespostaDoSpring<UsuarioTarefaEntityTo>> {

    const requestUrl = `${URL.baseUrl}${URL.usuarioTarefaByTarefaId_Get}`
      .replace(URL.usuarioTarefaByTarefaId_Get_Par1, '' + tarefaId);

    return this.http.get<PaginaDeRespostaDoSpring<UsuarioTarefaEntityTo>>(requestUrl, {
      params: new HttpParams()
        .set('sort', sort + ',' + order)
        .set('page', '' + page)
        .set('size', '' + pageSize)
    });
  }

  saveOrCreate(usuario: UsuarioTarefaEntityTo): Observable<UsuarioTarefaEntityTo> {

    const requestUrl = `${URL.baseUrl}${URL.usuarioTarefaSaveOrCreate_Post}`;
    return this.http.post<UsuarioTarefaEntityTo>(requestUrl, usuario);
  }

  remove(id: number): Observable<void> {

    const requestUrl = `${URL.baseUrl}${URL.usuarioTarefaRemove_Delete}`
      .replace(URL.usuarioTarefaRemove_Delete_Par1, '' + id);

    return this.http.delete<void>(requestUrl);
  }

}
