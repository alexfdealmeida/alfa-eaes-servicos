using System.Threading;
using System.Threading.Tasks;
using MediatR;

namespace Tarefa.Domain.Entities.Tarefa.Events
{
    public class TarefaEventHandler :
        INotificationHandler<TarefaRegistradoEvent>,
        INotificationHandler<TarefaAtualizadoEvent>,
        INotificationHandler<TarefaExcluidoEvent>
    {
        public Task Handle(TarefaRegistradoEvent message, CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }

        public Task Handle(TarefaAtualizadoEvent message, CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }

        public Task Handle(TarefaExcluidoEvent message, CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }
    }
}