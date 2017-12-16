using System;
using Tarefa.Domain.Core.Events;

namespace Tarefa.Domain.Entities.Tarefa.Events
{
    public abstract class BaseTarefaEvent : Event
    {
        public long Id { get; protected set; }
        public string Titulo { get; protected set; }
        public string Descricao { get; protected set; }
        public DateTime? Encerramento { get; protected set; }
        public DateTime? Inicio { get; protected set; }

    }
}