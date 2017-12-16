using System;

namespace Usuario.Domain.Entities.Usuarios.Commands
{
    public class RegistrarUsuarioCommand : BaseUsuarioCommand
    {
        public RegistrarUsuarioCommand(
            string nome,
            string email
            )
        {
            Nome = nome;
            Email = email;
        }
    }
}