using System;
using Tarefa.Domain.Core.Commands;

namespace Tarefa.Domain.Entities.Tarefa.Commands
{
    public abstract class BaseTarefaCommand : Command
    {
        public long Id { get; protected set; }
        public string Titulo { get; protected set; }
        public string Descricao { get; protected set; }
        public DateTime? Inicio { get; protected set; }
        public DateTime? Encerramento { get; protected set; }
    }
}