import { Alocacoes } from "./alocacoes";
import { Aluno } from "./aluno";
import { Tarefa } from "./tarefa";

export class Comentario {

    constructor() { }

    public Id: number;
    public Tarefa: Tarefa;
    public Aluno: Aluno;
    public Comentario: string;
    public Data: Date;
}