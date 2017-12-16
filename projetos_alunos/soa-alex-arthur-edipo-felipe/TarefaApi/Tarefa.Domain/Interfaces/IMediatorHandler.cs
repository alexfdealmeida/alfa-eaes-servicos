using System.Threading.Tasks;
using Tarefa.Domain.Core.Commands;
using Tarefa.Domain.Core.Events;

namespace Tarefa.Domain.Interfaces
{
    public interface IMediatorHandler
    {
        Task PublicarTarefa<T>(T tarefa) where T : Event;
        Task EnviarComando<T>(T comando) where T : Command;
    }
}