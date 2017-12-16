using System;
using System.Threading;
using System.Threading.Tasks;
using MediatR;
using Usuario.Domain.Core.Notifications;
using Usuario.Domain.Entities.Usuarios.Events;
using Usuario.Domain.Entities.Usuarios.Repository;
using Usuario.Domain.Handlers;
using Usuario.Domain.Interfaces;

namespace Usuario.Domain.Entities.Usuarios.Commands
{
    public class UsuarioCommandHandler : CommandHandler,
        INotificationHandler<RegistrarUsuarioCommand>,
        INotificationHandler<AtualizarUsuarioCommand>,
        INotificationHandler<ExcluirUsuarioCommand>

    {
        private readonly IUsuarioRepository _usuarioRepository;
        private readonly IMediatorHandler _mediator;

        public UsuarioCommandHandler(IUsuarioRepository usuarioRepository,
                                    IUnitOfWork uow,
                                    INotificationHandler<DomainNotification> notifications, 
                                    IMediatorHandler mediator) : base(uow, mediator, notifications)
        {
            _usuarioRepository = usuarioRepository;
            _mediator = mediator;
        }

        public Task Handle(RegistrarUsuarioCommand message, CancellationToken cancellationToken)
        {
            var usuario = Usuario.UsuarioFactory.NovoUsuarioCompleto(message.Id, message.Nome, message.Email);

            if (!UsuarioValido(usuario)) return Task.FromCanceled(cancellationToken);

            // TODO:
            // Validacoes de negocio!
            // Organizador pode registrar usuario?

            _usuarioRepository.Adicionar(usuario);

            if (Commit())
            {
                _mediator.PublicarUsuario(new UsuarioRegistradoEvent(usuario.Id,usuario.Nome,usuario.Email));
            }
            return Task.CompletedTask;
        }

        public Task Handle(AtualizarUsuarioCommand message, CancellationToken cancellationToken)
        {
            if (!UsuarioExistente(message.Id, message.MessageType)) return Task.FromCanceled(cancellationToken);

            var usuario = Usuario.UsuarioFactory.NovoUsuarioCompleto(message.Id, message.Nome, message.Email);

            if (!UsuarioValido(usuario)) return Task.FromCanceled(cancellationToken);

            _usuarioRepository.Atualizar(usuario);

            if (Commit())
            {
                _mediator.PublicarUsuario(new UsuarioAtualizadoEvent(usuario.Id, usuario.Nome, usuario.Email));
            }
            return Task.CompletedTask;
        }

        public Task Handle(ExcluirUsuarioCommand message, CancellationToken cancellationToken)
        {
            if (!UsuarioExistente(message.Id, message.MessageType)) return Task.FromCanceled(cancellationToken);
            var usuarioAtual = _usuarioRepository.ObterPorId(message.Id);

            // Validacoes de negocio
            usuarioAtual.ExcluirUsuario();

            _usuarioRepository.Remover(usuarioAtual.Id);

            if (Commit())
            {
                _mediator.PublicarUsuario(new UsuarioExcluidoEvent(message.Id));
            }
            return Task.CompletedTask;
        }

        private bool UsuarioValido(Usuario usuario)
        {
            if (usuario.EhValido()) return true;

            NotificarValidacoesErro(usuario.ValidationResult);
            return false;
        }

        private bool UsuarioExistente(long id, string messageType)
        {
            var usuario = _usuarioRepository.ObterPorId(id);

            if (usuario != null) return true;

            _mediator.PublicarUsuario(new DomainNotification(messageType, "Usuario não encontrado."));
            return false;
        }
    }
}