import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { UsuarioEntityTo } from '../model/to/to';

import { URL } from '../model/constantes/constantes';
import { PaginaDeRespostaDoSpring } from '../model/to/pagina-de-resposta-do-spring';

@Injectable()
export class UsuarioService {

  constructor(private http: HttpClient) { }

  findAll(sort: string, order: string, page: number)
    : Observable<PaginaDeRespostaDoSpring<UsuarioEntityTo>> {

    const requestUrl = `${URL.baseUrl}${URL.usuariosAll_Get}`;

    return this.http.get<PaginaDeRespostaDoSpring<UsuarioEntityTo>>(requestUrl, {
      params: new HttpParams()
        .set('sort', sort + ',' + order)
        .set('page', '' + page)
    });
  }

  saveOrCreate(usuario: UsuarioEntityTo): Observable<UsuarioEntityTo> {

    const requestUrl = `${URL.baseUrl}${URL.usuariosSaveOrCreate_Post}`;
    return this.http.post<UsuarioEntityTo>(requestUrl, usuario);
  }

  remove(id: number): Observable<void> {

    const requestUrl = `${URL.baseUrl}${URL.usuariosRemove_Delete}`
      .replace(URL.usuariosRemove_Delete_Par1, '' + id);

    return this.http.delete<void>(requestUrl);
  }

}
