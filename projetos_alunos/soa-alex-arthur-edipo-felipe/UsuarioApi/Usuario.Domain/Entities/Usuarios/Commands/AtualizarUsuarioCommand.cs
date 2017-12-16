using System;

namespace Usuario.Domain.Entities.Usuarios.Commands
{
    public class AtualizarUsuarioCommand : BaseUsuarioCommand
    {
        public AtualizarUsuarioCommand(
            long id,
            string nome,
            string email)
        {
            Id = id;
            Nome = nome;
            Email = email;
        }
    }
}