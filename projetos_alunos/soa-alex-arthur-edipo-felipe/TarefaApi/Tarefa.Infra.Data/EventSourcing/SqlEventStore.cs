using Newtonsoft.Json;
using Tarefa.Domain.Core.Events;
using Tarefa.Domain.Interfaces;
using Tarefa.Infra.Data.Repository.EventSourcing;

namespace Tarefa.Infra.Data.EventSourcing
{
    public class SqlEventStore : IEventStore
    {
        private readonly IEventStoreRepository _eventStoreRepository;

        public SqlEventStore(IEventStoreRepository eventStoreRepository)
        {
            _eventStoreRepository = eventStoreRepository;
        }

        public void SalvarTarefa<T>(T tarefa) where T : Event
        {
            var serializedData = JsonConvert.SerializeObject(tarefa);

            var storedEvent = new StoredEvent(
                tarefa,
                serializedData);

            _eventStoreRepository.Store(storedEvent);
        }
    }
}