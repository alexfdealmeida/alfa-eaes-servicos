using System;

namespace Tarefa.Domain.Entities.Tarefa.Commands
{
    public class RegistrarTarefaCommand : BaseTarefaCommand
    {
        public RegistrarTarefaCommand(
            string titulo,
            string descricao,
            DateTime? inicio,
            DateTime? final
        )
        {
            Titulo = titulo;
            Descricao = descricao;
            Inicio = inicio;
            Encerramento = final;
        }
    }
}