using System;
using MediatR;

namespace Usuario.Domain.Core.Events
{
    public abstract class Message : INotification
    {
        public string MessageType { get; protected set; }
        public long AggregateId { get; protected set; }

        protected Message()
        {
            MessageType = GetType().Name;
        }
    }
}