using System;

namespace Usuario.Domain.Entities.Usuarios.Commands
{
    public class ExcluirUsuarioCommand : BaseUsuarioCommand
    {
        public ExcluirUsuarioCommand(long id)
        {
            Id = id;
            AggregateId = Id;
        }
    }
}