using System;
using Usuario.Domain.Core.Commands;

namespace Usuario.Domain.Entities.Usuarios.Commands
{
    public abstract class BaseUsuarioCommand : Command
    {
        public long Id { get; protected set; }
        public string Nome { get; protected set; }
        public string Email { get; protected set; }
    }
}