using System;
using FluentValidation;
using Tarefa.Domain.Core.Models;

namespace Tarefa.Domain.Entities.Tarefa
{
    public class Tarefa : Entity<Tarefa>
    {
        public Tarefa(
            string titulo, 
            string descricao, 
            DateTime inicio,
            DateTime final
            )
        {
            Id = 0;
            Titulo = titulo;
            Descricao = descricao;
            Inicio = Inicio;
            Encerramento = final;
        }

        private Tarefa() {}

        public string Titulo { get; private set; }
        public string Descricao { get; private set; }
        public DateTime? Encerramento { get; private set; }
        public DateTime? Inicio { get; private set; }

        // EF propriedades de navegacao
        
        public void ExcluirTarefa()
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
            ValidarDescricao();
            ValidationResult = Validate(this);

        }

        private void ValidarNome()
        {
            RuleFor(c => c.Titulo)
                .NotEmpty().WithMessage("O título da tarefa precisa ser fornecido")
                .Length(2, 255).WithMessage("O título da tarefa precisa ter entre 2 e 255 caracteres");
        }

        private void ValidarDescricao()
        {
            RuleFor(c => c.Titulo)
                .NotEmpty().WithMessage("A descrição da tarefa precisa ser fornecido")
                .Length(2, 255).WithMessage("O descrição da tarefa precisa ter entre 2 e 255 caracteres");
        }

        #endregion

        public static class TarefaFactory
        {
            public static Tarefa NovoTarefaCompleto(long id, string titulo, string descricao, DateTime? inicio, DateTime? final)
            {
                var tarefa = new Tarefa()
                {
                    Id = id,
                    Titulo = titulo,
                    Descricao = descricao,
                    Inicio = inicio,
                    Encerramento = final,
                };

            
                return tarefa;
            }
        }
    }
}
