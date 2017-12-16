using System;
using System.Collections.Generic;
using Usuario.Domain.Core.Events;

namespace Usuario.Infra.Data.Repository.EventSourcing
{
    public interface IEventStoreRepository : IDisposable
    {
        void Store(StoredEvent theEvent);
        IList<StoredEvent> All(long aggregateId);
    }
}