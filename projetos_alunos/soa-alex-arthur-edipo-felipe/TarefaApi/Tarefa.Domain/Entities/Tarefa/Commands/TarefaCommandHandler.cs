using System.Threading;
using System.Threading.Tasks;
using MediatR;
using Tarefa.Domain.Core.Notifications;
using Tarefa.Domain.Entities.Tarefa.Events;
using Tarefa.Domain.Entities.Tarefa.Repository;
using Tarefa.Domain.Handlers;
using Tarefa.Domain.Interfaces;

namespace Tarefa.Domain.Entities.Tarefa.Commands
{
    public class TarefaCommandHandler : CommandHandler,
        INotificationHandler<RegistrarTarefaCommand>,
        INotificationHandler<AtualizarTarefaCommand>,
        INotificationHandler<ExcluirTarefaCommand>

    {
        private readonly ITarefaRepository _tarefaRepository;
        private readonly IMediatorHandler _mediator;

        public TarefaCommandHandler(ITarefaRepository tarefaRepository,
                                    IUnitOfWork uow,
                                    INotificationHandler<DomainNotification> notifications, 
                                    IMediatorHandler mediator) : base(uow, mediator, notifications)
        {
            _tarefaRepository = tarefaRepository;
            _mediator = mediator;
        }

        public Task Handle(RegistrarTarefaCommand message, CancellationToken cancellationToken)
        {
            var tarefa = Tarefa.TarefaFactory.NovoTarefaCompleto(message.Id, message.Titulo, message.Descricao, message.Inicio, message.Encerramento);

            if (!TarefaValido(tarefa)) return Task.FromCanceled(cancellationToken);

            // TODO:
            // Validacoes de negocio!
            // Organizador pode registrar tarefa?

            _tarefaRepository.Adicionar(tarefa);

            if (Commit())
            {
                _mediator.PublicarTarefa(new TarefaRegistradoEvent(tarefa.Id, tarefa.Titulo, tarefa.Descricao, tarefa.Inicio, tarefa.Encerramento));
            }
            return Task.CompletedTask;
        }

        public Task Handle(AtualizarTarefaCommand message, CancellationToken cancellationToken)
        {
            if (!TarefaExistente(message.Id, message.MessageType)) return Task.FromCanceled(cancellationToken);

            var tarefa = Tarefa.TarefaFactory.NovoTarefaCompleto(message.Id, message.Titulo, message.Descricao, message.Inicio, message.Encerramento);

            if (!TarefaValido(tarefa)) return Task.FromCanceled(cancellationToken);

            _tarefaRepository.Atualizar(tarefa);

            if (Commit())
            {
                _mediator.PublicarTarefa(new TarefaAtualizadoEvent(tarefa.Id, tarefa.Titulo, tarefa.Descricao, tarefa.Inicio, tarefa.Encerramento));
            }
            return Task.CompletedTask;
        }

        public Task Handle(ExcluirTarefaCommand message, CancellationToken cancellationToken)
        {
            if (!TarefaExistente(message.Id, message.MessageType)) return Task.FromCanceled(cancellationToken);
            var tarefaAtual = _tarefaRepository.ObterPorId(message.Id);

            // Validacoes de negocio
            tarefaAtual.ExcluirTarefa();

            _tarefaRepository.Remover(tarefaAtual.Id);

            if (Commit())
            {
                _mediator.PublicarTarefa(new TarefaExcluidoEvent(message.Id));
            }
            return Task.CompletedTask;
        }

        private bool TarefaValido(Tarefa tarefa)
        {
            if (tarefa.EhValido()) return true;

            NotificarValidacoesErro(tarefa.ValidationResult);
            return false;
        }

        private bool TarefaExistente(long id, string messageType)
        {
            var tarefa = _tarefaRepository.ObterPorId(id);

            if (tarefa != null) return true;

            _mediator.PublicarTarefa(new DomainNotification(messageType, "Tarefa não encontrado."));
            return false;
        }
    }
}