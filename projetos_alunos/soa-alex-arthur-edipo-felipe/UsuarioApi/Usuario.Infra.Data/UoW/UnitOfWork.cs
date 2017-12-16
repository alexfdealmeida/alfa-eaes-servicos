using Usuario.Domain.Interfaces;
using Usuario.Infra.Data.Context;

namespace Usuario.Infra.Data.UoW
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly UsuariosContext _context;

        public UnitOfWork(UsuariosContext context)
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