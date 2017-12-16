using System;
using System.Collections.Generic;
using FluentValidation;
using Usuario.Domain.Core.Models;

namespace Usuario.Domain.Entities.Usuarios
{
    public class Usuario : Entity<Usuario>
    {
        public Usuario(
            string nome, 
            string email)
        {
            Id = 0;
            Nome = nome;
            Email = email;
        }

        private Usuario() {}

        public string Nome { get; private set; }
        public string Email { get; private set; }

        // EF propriedades de navegacao
        
        public void ExcluirUsuario()
        {
            // TODO: Deve validar alguma regra?
        }

        public override bool EhValido()
        {
            Validar();
            return ValidationResult.IsValid;
        }

        #region Validações

        private void Validar()
        {
            ValidarNome();
            ValidarEmail();
            ValidationResult = Validate(this);

        }

        private void ValidarNome()
        {
            RuleFor(c => c.Nome)
                .NotEmpty().WithMessage("O nome do usuario precisa ser fornecido")
                .Length(2, 255).WithMessage("O nome do usuario precisa ter entre 2 e 255 caracteres");
        }

        private void ValidarEmail()
        {
            
        }

        #endregion

        public static class UsuarioFactory
        {
            public static Usuario NovoUsuarioCompleto(long id, string nome, string email)
            {
                var usuario = new Usuario()
                {
                    Id = id,
                    Nome = nome,
                    Email = email,
                };

            
                return usuario;
            }
        }
    }
}
