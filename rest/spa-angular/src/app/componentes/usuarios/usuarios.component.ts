import { Component, ViewChild, AfterViewInit, Output, EventEmitter, ElementRef } from '@angular/core';
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

import { UsuarioService } from '../../core/servicos/usuario.service';

import { UsuarioEntityTo, PaginaDeRespostaDoSpring } from '../../core/model/to/to';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements AfterViewInit {
  @ViewChild(FormGroupDirective) _outerFormDirective;
  _outerForm: FormGroup;

  @ViewChild('elementToFocus') _elementToFocus: ElementRef;

  _definicaoDasColunas = ['nome', 'email', 'colunaDeOpcoes'];
  _dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) _paginator: MatPaginator;
  @ViewChild(MatSort) _sort: MatSort;

  _pageSize = 10;
  _resultsLength = 0;
  _isLoadingResults = false;
  private _usuario: UsuarioEntityTo;

  @Output() usuariosChanged = new EventEmitter<UsuarioEntityTo[]>();

  private _shoudSubscribe = true;
  private _subscription: Subscription;
  private _myTableObservable: Observable<any>;

  constructor(private _usuarioService: UsuarioService, _fb: FormBuilder) {
    this._outerForm = _fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
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
        return this._usuarioService!.findAll(this._sort.active,
          this._sort.direction, this._paginator.pageIndex, this._pageSize);
      }),
      map((data: PaginaDeRespostaDoSpring<UsuarioEntityTo>) => {
        // Troca os flags para mostrar que os resultados já chegaram.
        setTimeout(() => this._isLoadingResults = false, 0);
        this._resultsLength = data.totalElements;

        return data.content;
      }),
      catchError(() => {
        this._isLoadingResults = false;
        return observableOf([]);
      })
      );
  }

  private _subscribeToChanges() {
    if (!this._shoudSubscribe) { return; }

    this._subscription = this._myTableObservable.subscribe((data: UsuarioEntityTo[]) => {
      this._dataSource.data = data;
      this.usuariosChanged.emit(data);
    });
  }

  private _loadValueToInstanceUsuario(usuario: UsuarioEntityTo) {
    this._usuario = usuario ? JSON.parse(JSON.stringify(usuario)) : null;
  }

  private _loadInstanceUsuarioToForm() {
    if (!!this._usuario) {
      this._outerForm.patchValue(this._usuario);
    } else {
      this._elementToFocus.nativeElement.focus();
      setTimeout(() =>  this._outerFormDirective.resetForm());
    }
  }

  _altera(usuario: UsuarioEntityTo) {
    this._loadValueToInstanceUsuario(usuario);
    this._loadInstanceUsuarioToForm();
  }

  _grava() {
    if (!this._usuario) {
      this._usuario = new UsuarioEntityTo();
    }

    this._usuario.nome = this._outerForm.value.nome;
    this._usuario.email = this._outerForm.value.email;

    this._usuarioService.saveOrCreate(this._usuario)
      .pipe(
      catchError(e => observableOf(null))
      )
      .subscribe((data: UsuarioEntityTo) => {
        if (!data) {
          return;
        }

        this._carregaUsuarios();
        this._limpa();
      });
  }

  _remove(usuarioId: number) {
    this._usuarioService.remove(usuarioId)
      .subscribe(() => {
        this._carregaUsuarios();
      });
  }

  _cancela() {
    this._loadInstanceUsuarioToForm();
  }

  _limpa() {
    this._loadValueToInstanceUsuario(null);
    this._loadInstanceUsuarioToForm();
  }

  _carregaUsuarios() {
    // força recarga dos dados
    if (!this._isValidSubscription) { this._subscribeToChanges(); }
    this._paginator.page.next(new PageEvent());
  }

  private get _isValidSubscription(): boolean {
    return !!this._subscription && !this._subscription.closed;
  }

}
