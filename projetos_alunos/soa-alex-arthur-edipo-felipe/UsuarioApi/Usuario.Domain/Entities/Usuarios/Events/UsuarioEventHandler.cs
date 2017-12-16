using System.Threading;
using System.Threading.Tasks;
using MediatR;

namespace Usuario.Domain.Entities.Usuarios.Events
{
    public class UsuarioEventHandler :
        INotificationHandler<UsuarioRegistradoEvent>,
        INotificationHandler<UsuarioAtualizadoEvent>,
        INotificationHandler<UsuarioExcluidoEvent>
    {
        public Task Handle(UsuarioRegistradoEvent message, CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }

        public Task Handle(UsuarioAtualizadoEvent message, CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }

        public Task Handle(UsuarioExcluidoEvent message, CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }
    }
}