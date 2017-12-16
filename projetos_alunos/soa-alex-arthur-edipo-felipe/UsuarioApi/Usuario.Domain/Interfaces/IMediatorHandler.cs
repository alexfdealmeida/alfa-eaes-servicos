using System.Threading.Tasks;
using Usuario.Domain.Core.Commands;
using Usuario.Domain.Core.Events;

namespace Usuario.Domain.Interfaces
{
    public interface IMediatorHandler
    {
        Task PublicarUsuario<T>(T usuario) where T : Event;
        Task EnviarComando<T>(T comando) where T : Command;
    }
}