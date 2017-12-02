import { Component, ViewChild, AfterViewInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {
  MatTableDataSource,
  MatPaginator,
  MatSort,
  PageEvent
} from '@angular/material';

import { Observable } from 'rxjs/Observable';
import { merge } from 'rxjs/observable/merge';
import { of as observableOf } from 'rxjs/observable/of';
import {
  catchError,
  map,
  startWith,
  switchMap
} from 'rxjs/operators';

import { AlocacaoDeTarefasService } from '../../core/servicos/alocacao-de-tarefas.service';

import {
  PaginaDeRespostaDoSpring,
  TarefaEntityTo,
  UsuarioEntityTo,
  UsuarioTarefaEntityTo,
} from '../../core/model/to/to';

@Component({
  selector: 'app-alocacao',
  templateUrl: './alocacao.component.html',
  styleUrls: ['./alocacao.component.scss']
})
export class AlocacaoComponent implements AfterViewInit {

  @Input() usuarios: UsuarioEntityTo[] = [];
  @Input() tarefas: UsuarioEntityTo[] = [];

  _outerForm: FormGroup;
  _definicaoDasColunas = ['tarefa', 'nome', 'colunaDeOpcoes'];
  _dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) _paginator: MatPaginator;
  @ViewChild(MatSort) _sort: MatSort;

  _pageSize = 10;
  _resultsLength = 0;
  _isLoadingResults = false;

  constructor(private _alocacaoDeTarefasService: AlocacaoDeTarefasService, _fb: FormBuilder) {
    this._outerForm = _fb.group({
      tarefa: ['', Validators.required],
      usuario: ['', [Validators.required]]
    });
  }

  ngAfterViewInit() {
    // If the user changes the sort order, reset back to the first page.
    this._sort.sortChange.subscribe(() => this._paginator.pageIndex = 0);

    merge(this._sort.sortChange, this._paginator.page)
      .pipe(
      startWith({}),
      switchMap(() => {
        setTimeout(() => this._isLoadingResults = true, 0);
        return this._alocacaoDeTarefasService!.findByTarefaId(this._outerForm.value.tarefa.id,
          'usuario.' + this._sort.active, this._sort.direction, this._paginator.pageIndex,
          this._pageSize);
      }),
      map((data: PaginaDeRespostaDoSpring<UsuarioTarefaEntityTo>) => {
        // Troca os flags para mostrar que os resultados já chegaram.
        setTimeout(() => this._isLoadingResults = false, 0);
        this._resultsLength = data.totalElements;

        return data.content;
      }),
      catchError(() => {
        setTimeout(() => this._isLoadingResults = false, 0);
        return observableOf([]);
      })
      ).subscribe((data: UsuarioTarefaEntityTo[]) => {
        this._dataSource.data = data;
      });
  }

  _grava() {
    const to: UsuarioTarefaEntityTo = new UsuarioTarefaEntityTo();
    to.tarefa = this._outerForm.value.tarefa;
    to.usuario = this._outerForm.value.usuario;

    this._alocacaoDeTarefasService.saveOrCreate(to)
      .pipe(
      catchError(e => observableOf(null))
      )
      .subscribe((data: UsuarioEntityTo) => {
        if (!data) {
          return;
        }

        this._outerForm.controls.usuario.setValue(null);
        this._carregaUsuarios();
      });
  }

  _remove(usuarioId: number) {
    this._alocacaoDeTarefasService.remove(usuarioId)
      .subscribe(() => {
        this._carregaUsuarios();
      });
  }

  _carregaUsuarios() {
    // força recarga dos dados
    this._paginator.page.next(new PageEvent());
  }

  _findUsuariosDaTarefa(tarefa: TarefaEntityTo) {
    // força recarga dos dados
    this._paginator.page.next(new PageEvent());
  }

}
