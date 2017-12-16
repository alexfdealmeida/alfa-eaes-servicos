using System;

namespace Tarefa.Domain.Entities.Tarefa.Events
{
    public class TarefaRegistradoEvent : BaseTarefaEvent
    {
        public TarefaRegistradoEvent(
            long id,
            string titulo,
            string descricao,
            DateTime? inicio,
            DateTime? final
            )
        {
            Id = id;
            Titulo = titulo;
            Descricao = descricao;
            Inicio = inicio;
            Encerramento = final;

            AggregateId = id;
        }
    }
}