using Tarefa.Domain.Interfaces;
using Tarefa.Infra.Data.Context;

namespace Tarefa.Infra.Data.UoW
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly TarefasContext _context;

        public UnitOfWork(TarefasContext context)
        {
            _context = context;
        }

        public bool Commit()
        {
            return _context.SaveChanges() > 0;
        }

        public void Dispose()
        {
            _context.Dispose();
        }
    }
}