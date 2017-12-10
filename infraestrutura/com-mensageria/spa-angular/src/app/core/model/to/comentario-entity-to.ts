import { AbstractEntityTo } from './abstract-entity-to';
import { TarefaEntityTo } from './tarefa-entity-to';
import { UsuarioEntityTo } from './usuario-entity-to';

export class ComentarioEntityTo extends AbstractEntityTo {
    usuario: UsuarioEntityTo;
    tarefa: TarefaEntityTo;
    corpo = '';
    data: Date = new Date();
}
