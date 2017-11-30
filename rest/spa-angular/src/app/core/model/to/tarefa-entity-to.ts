import { AbstractEntityTo } from './abstract-entity-to';

export class TarefaEntityTo extends AbstractEntityTo {
    titulo = '';
    descricao = '';
    dataInicio: Date = new Date();
    dataFim: Date = new Date();
}
