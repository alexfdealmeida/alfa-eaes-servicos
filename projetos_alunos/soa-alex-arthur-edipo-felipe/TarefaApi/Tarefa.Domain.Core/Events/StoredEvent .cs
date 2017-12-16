using System;

namespace Tarefa.Domain.Core.Events
{
    public class StoredEvent : Event
    {
        public StoredEvent(Event tarefa, string data)
        {
            Id = Guid.NewGuid();
            AggregateId = tarefa.AggregateId;
            MessageType = tarefa.MessageType;
            Data = data;
        }

        // EF Constructor
        protected StoredEvent() { }

        public Guid Id { get; private set; }

        public string Data { get; private set; }
    }
}