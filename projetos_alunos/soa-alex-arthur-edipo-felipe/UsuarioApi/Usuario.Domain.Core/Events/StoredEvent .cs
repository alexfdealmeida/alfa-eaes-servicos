using System;

namespace Usuario.Domain.Core.Events
{
    public class StoredEvent : Event
    {
        public StoredEvent(Event usuario, string data)
        {
            Id = Guid.NewGuid();
            AggregateId = usuario.AggregateId;
            MessageType = usuario.MessageType;
            Data = data;
        }

        // EF Constructor
        protected StoredEvent() { }

        public Guid Id { get; private set; }

        public string Data { get; private set; }
    }
}