using System;

namespace Usuario.Domain.Entities.Usuarios.Events
{
    public class UsuarioRegistradoEvent : BaseUsuarioEvent
    {
        public UsuarioRegistradoEvent(
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