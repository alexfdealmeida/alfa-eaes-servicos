﻿using System;
using Tarefa.Domain.Core.Events;

namespace Tarefa.Domain.Core.Notifications
{
    public class DomainNotification : Event
    {
        public Guid DomainNotificationId { get; private set; }
        public string Key { get; private set; }
        public string Value { get; private set; }
        public int Version { get; private set; }

        public DomainNotification(string key, string value)
        {
            DomainNotificationId = Guid.NewGuid();
            Key = key;
            Value = value;
            Version = 1;
        }
    }
}