using System;

namespace Usuario.Domain.Entities.Usuarios.Events
{
    public class UsuarioExcluidoEvent : BaseUsuarioEvent
    {
        public UsuarioExcluidoEvent(long id)
        {
            Id = id;
            AggregateId = id;
        }
    }
}