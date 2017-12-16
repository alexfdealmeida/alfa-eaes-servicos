using Usuario.Domain.Core.Events;

namespace Usuario.Domain.Interfaces
{
    public interface IEventStore
    {
        void SalvarUsuario<T>(T usuario) where T : Event;
    }
}