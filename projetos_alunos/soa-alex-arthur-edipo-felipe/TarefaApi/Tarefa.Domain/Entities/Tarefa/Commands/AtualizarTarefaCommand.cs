using System;

namespace Tarefa.Domain.Entities.Tarefa.Commands
{
    public class AtualizarTarefaCommand : BaseTarefaCommand
    {
        public AtualizarTarefaCommand(
            long id,
            string titulo,
            string descricao,
            DateTime? inicio,
            DateTime? final
        )
        {
            Id = id;
            Titulo = titulo;
            Descricao = descricao;
            Inicio = inicio;
            Encerramento = final;

        }
    }
}