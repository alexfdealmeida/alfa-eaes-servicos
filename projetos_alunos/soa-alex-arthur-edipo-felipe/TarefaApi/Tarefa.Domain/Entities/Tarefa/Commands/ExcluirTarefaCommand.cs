namespace Tarefa.Domain.Entities.Tarefa.Commands
{
    public class ExcluirTarefaCommand : BaseTarefaCommand
    {
        public ExcluirTarefaCommand(long id)
        {
            Id = id;
            AggregateId = Id;
        }
    }
}