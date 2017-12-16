using System;
using Usuario.Domain.Core.Events;

namespace Usuario.Domain.Entities.Usuarios.Events
{
    public abstract class BaseUsuarioEvent : Event
    {
        public long Id { get; protected set; }
        public string Nome { get; protected set; }
        public string Email { get; protected set; }
    }
}