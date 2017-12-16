using System;
using System.Collections.Generic;
using Tarefa.Domain.Core.Events;

namespace Tarefa.Infra.Data.Repository.EventSourcing
{
    public interface IEventStoreRepository : IDisposable
    {
        void Store(StoredEvent theEvent);
        IList<StoredEvent> All(long aggregateId);
    }
}