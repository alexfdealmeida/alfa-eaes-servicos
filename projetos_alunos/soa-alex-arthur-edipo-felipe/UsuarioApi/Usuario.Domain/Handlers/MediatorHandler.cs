using System.Threading.Tasks;
using MediatR;
using Usuario.Domain.Core.Commands;
using Usuario.Domain.Core.Events;
using Usuario.Domain.Interfaces;

namespace Usuario.Domain.Handlers
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

        public Task PublicarUsuario<T>(T usuario) where T : Event
        {
            if (!usuario.MessageType.Equals("DomainNotification"))
                _eventStore?.SalvarUsuario(usuario);

            return Publicar(usuario);
        }

        private Task Publicar<T>(T mensagem) where T : Message
        {
            return _mediator.Publish(mensagem);
        }
    }
}