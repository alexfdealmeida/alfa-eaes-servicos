using Tarefa.Domain.Core.Events;

namespace Tarefa.Domain.Interfaces
{
    public interface IEventStore
    {
        void SalvarTarefa<T>(T tarefa) where T : Event;
    }
}