using Newtonsoft.Json;
using Usuario.Domain.Core.Events;
using Usuario.Domain.Interfaces;
using Usuario.Infra.Data.Repository.EventSourcing;

namespace Usuario.Infra.Data.EventSourcing
{
    public class SqlEventStore : IEventStore
    {
        private readonly IEventStoreRepository _eventStoreRepository;

        public SqlEventStore(IEventStoreRepository eventStoreRepository)
        {
            _eventStoreRepository = eventStoreRepository;
        }

        public void SalvarUsuario<T>(T usuario) where T : Event
        {
            var serializedData = JsonConvert.SerializeObject(usuario);

            var storedEvent = new StoredEvent(
                usuario,
                serializedData);

            _eventStoreRepository.Store(storedEvent);
        }
    }
}