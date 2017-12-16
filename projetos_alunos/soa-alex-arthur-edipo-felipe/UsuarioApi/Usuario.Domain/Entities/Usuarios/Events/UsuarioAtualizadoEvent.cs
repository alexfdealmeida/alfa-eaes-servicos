using System;

namespace Usuario.Domain.Entities.Usuarios.Events
{
    public class UsuarioAtualizadoEvent : BaseUsuarioEvent
    {
        public UsuarioAtualizadoEvent(
            long id,
            string nome,
            string email)
        {
            Id = id;
            Nome = nome;
            Email = email;

            AggregateId = id;
        }
    }
}