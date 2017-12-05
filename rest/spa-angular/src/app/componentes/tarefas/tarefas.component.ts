import { Component, ViewChild, AfterViewInit, Output, EventEmitter } from '@angular/core';
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

import { TarefaService } from '../../core/servicos/tarefa.service';

import { TarefaEntityTo, PaginaDeRespostaDoSpring } from '../../core/model/to/to';

@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.component.html',
  styleUrls: ['./tarefas.component.scss']
})
export class TarefasComponent implements AfterViewInit {
  @ViewChild(FormGroupDirective) _outerFormDirective;
  _outerForm: FormGroup;
  _definicaoDasColunas = ['titulo', 'descricao', 'colunaDeOpcoes'];
  _dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) _paginator: MatPaginator;
  @ViewChild(MatSort) _sort: MatSort;

  _pageSize = 10;
  _resultsLength = 0;
  _isLoadingResults = false;
  private _tarefa: TarefaEntityTo;

  @Output() tarefasChanged = new EventEmitter<TarefaEntityTo[]>();

  private _shoudSubscribe = true;
  private _subscription: Subscription;
  private _myTableObservable: Observable<any>;

  constructor(private _tarefaService: TarefaService, _fb: FormBuilder) {
    this._outerForm = _fb.group({
      titulo: ['', Validators.required],
      dataInicio: [new Date(), Validators.required],
      dataFim: [new Date(), Validators.required],
      descricao: ['', Validators.required]
    });
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
        return this._tarefaService!.findAll(this._sort.active,
          this._sort.direction, this._paginator.pageIndex, this._pageSize);
      }),
      map((data: PaginaDeRespostaDoSpring<TarefaEntityTo>) => {
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

    this._subscription = this._myTableObservable.subscribe((data: TarefaEntityTo[]) => {
      this._dataSource.data = data;
      this.tarefasChanged.emit(data);
    });
  }

  private _loadValueToInstanceTarefa(tarefa: TarefaEntityTo) {
    this._tarefa = !!tarefa ? JSON.parse(JSON.stringify(tarefa)) : null;
  }

  private _loadInstanceTarefaToForm() {
    if (!!this._tarefa) {
      this._tarefa.dataFim = new Date(this._tarefa.dataFim);
      this._tarefa.dataInicio = new Date(this._tarefa.dataInicio);
      this._outerForm.patchValue(this._tarefa);
    } else {
      this._outerFormDirective.resetForm();
    }
  }

  _altera(tarefa: TarefaEntityTo) {
    this._loadValueToInstanceTarefa(tarefa);
    this._loadInstanceTarefaToForm();
  }

  _grava(formDirective: FormGroupDirective) {
    if (!this._tarefa) {
      this._tarefa = new TarefaEntityTo();
    }

    this._tarefa.titulo = this._outerForm.value.titulo;
    this._tarefa.descricao = this._outerForm.value.descricao;
    this._tarefa.dataInicio = this._outerForm.value.dataInicio;
    this._tarefa.dataFim = this._outerForm.value.dataFim;

    this._tarefaService.saveOrCreate(this._tarefa)
      .pipe(
      catchError(e => observableOf(null))
      )
      .subscribe((data: TarefaEntityTo) => {
        if (!data) {
          return;
        }

        this._carregaTarefas();
        this._limpa();
      });
  }

  _remove(tarefaId: number) {
    this._tarefaService.remove(tarefaId)
      .subscribe(() => {
        this._carregaTarefas();
      });
  }

  _cancela() {
    this._loadInstanceTarefaToForm();
  }

  _limpa() {
    this._loadValueToInstanceTarefa(null);
    this._loadInstanceTarefaToForm();
  }

  _carregaTarefas() {
    // força recarga dos dados
    if (!this._isValidSubscription) { this._subscribeToChanges(); }
    this._paginator.page.next(new PageEvent());
  }

  private get _isValidSubscription(): boolean {
    return !!this._subscription && !this._subscription.closed;
  }

}
