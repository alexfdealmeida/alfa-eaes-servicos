import { Component, ViewChild, AfterViewInit, OnDestroy, Output, EventEmitter, Input, ElementRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormGroupDirective } from '@angular/forms';
import {
  MatTableDataSource,
  MatPaginator,
  MatSort,
  PageEvent
} from '@angular/material';

import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
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
export class AlocacaoComponent implements AfterViewInit, OnDestroy {
  @ViewChild(FormGroupDirective) _outerFormDirective;
  _outerForm: FormGroup;

  @ViewChild('elementToFocus') _elementToFocus: ElementRef;

  @Input() usuarios: UsuarioEntityTo[] = [];
  @Input() tarefas: UsuarioEntityTo[] = [];

  _definicaoDasColunas = ['tarefa', 'nome', 'colunaDeOpcoes'];
  _dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) _paginator: MatPaginator;
  @ViewChild(MatSort) _sort: MatSort;

  _pageSize = 10;
  _resultsLength = 0;
  _isLoadingResults = false;

  private _shoudSubscribe = true;
  private _subscription: Subscription;
  private _myTableObservable: Observable<any>;

  constructor(private _alocacaoDeTarefasService: AlocacaoDeTarefasService, _fb: FormBuilder) {
    this._outerForm = _fb.group({
      tarefa: ['', Validators.required],
      usuario: ['', [Validators.required]]
    });
  }

  ngOnDestroy() {
    this._shoudSubscribe = false;
  }

  ngAfterViewInit() {
    // If the user changes the sort order, reset back to the first page.
    this._sort.sortChange.subscribe(() => this._paginator.pageIndex = 0);

    this._myTableObservable = this._myObservable();

    this._subscribeToChanges();
  }

  private _myObservable(): Observable<any> {
    return merge(this._sort.sortChange, this._paginator.page)
      .pipe(
      startWith({}),
      switchMap(() => {
        setTimeout(() => this._isLoadingResults = true, 0);
        return this._outerForm.value.tarefa.id ?
          this._alocacaoDeTarefasService!.findByTarefaId(this._outerForm.value.tarefa.id,
            'usuario.' + this._sort.active, this._sort.direction, this._paginator.pageIndex,
            this._pageSize)
          : observableOf(null);
      }),
      map((data: PaginaDeRespostaDoSpring<UsuarioTarefaEntityTo>) => {
        if (!data) {
          return [];
        }

        // Troca os flags para mostrar que os resultados já chegaram.
        setTimeout(() => this._isLoadingResults = false, 0);
        this._resultsLength = data.totalElements;

        return data.content;
      }),
      catchError(() => {
        setTimeout(() => this._isLoadingResults = false, 0);
        return observableOf([]);
      })
      );
  }

  private _subscribeToChanges() {
    if (!this._shoudSubscribe) { return; }

    this._subscription = this._myTableObservable.subscribe((data: UsuarioTarefaEntityTo[]) => {
      this._dataSource.data = data;
    });
  }

  _grava() {
    const to: UsuarioTarefaEntityTo = new UsuarioTarefaEntityTo();
    to.tarefa = this._outerForm.value.tarefa;
    to.usuario = this._outerForm.value.usuario;

    this._alocacaoDeTarefasService.saveOrCreate(to)
      .pipe(catchError(e => observableOf(null)))
      .subscribe((data: UsuarioEntityTo) => {
        if (!data) {
          return;
        }
        this._outerFormDirective.resetForm();
        this._outerForm.patchValue({
          tarefa: to.tarefa
        })
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
    if (!this._isValidSubscription) { this._subscribeToChanges(); }
    this._paginator.page.next(new PageEvent());
  }

  private get _isValidSubscription(): boolean {
    return !!this._subscription && !this._subscription.closed;
  }

  _findUsuariosDaTarefa(tarefa: TarefaEntityTo) {
    // força recarga dos dados
    if (!!tarefa) {
      if (this._subscription.closed) { this._subscribeToChanges(); }
      this._paginator.page.next(new PageEvent());
    } else {
      this._outerForm.controls.usuario.setValue(null);
      this._dataSource.data = [];
      setTimeout(() => {
        this._elementToFocus.nativeElement.focus();
        this._outerFormDirective.resetForm();
      }, 0);
    }
  }

}
