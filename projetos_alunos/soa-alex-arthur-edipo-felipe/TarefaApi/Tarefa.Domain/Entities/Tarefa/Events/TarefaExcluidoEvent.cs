namespace Tarefa.Domain.Entities.Tarefa.Events
{
    public class TarefaExcluidoEvent : BaseTarefaEvent
    {
        public TarefaExcluidoEvent(long id)
        {
            Id = id;
            AggregateId = id;
        }
    }
}