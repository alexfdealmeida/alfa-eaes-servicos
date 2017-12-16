using System.Threading.Tasks;
using MediatR;
using Tarefa.Domain.Core.Commands;
using Tarefa.Domain.Core.Events;
using Tarefa.Domain.Interfaces;

namespace Tarefa.Domain.Handlers
{
    public class MediatorHandler : IMediatorHandler
    {
        private readonly IMediator _mediator;
        private readonly IEventStore _eventStore;

        public MediatorHandler(IMediator mediator, IEventStore eventStore)
        {
            _mediator = mediator;
            _eventStore = eventStore;
        }

        public Task EnviarComando<T>(T comando) where T : Command
        {
            return Publicar(comando);
        }

        public Task PublicarTarefa<T>(T tarefa) where T : Event
        {
            if (!tarefa.MessageType.Equals("DomainNotification"))
                _eventStore?.SalvarTarefa(tarefa);

            return Publicar(tarefa);
        }

        private Task Publicar<T>(T mensagem) where T : Message
        {
            return _mediator.Publish(mensagem);
        }
    }
}