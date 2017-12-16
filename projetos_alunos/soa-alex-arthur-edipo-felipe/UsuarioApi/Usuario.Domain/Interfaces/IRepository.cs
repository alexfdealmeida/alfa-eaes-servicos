using System;
using System.Collections.Generic;
using System.Linq.Expressions;

namespace Usuario.Domain.Interfaces
{
    public interface IRepository<TEntity> : IDisposable
    {
        void Adicionar(TEntity obj);
        TEntity ObterPorId(long id);
        IEnumerable<TEntity> ObterTodos();
        void Atualizar(TEntity obj);
        void Remover(long id);
        IEnumerable<TEntity> Buscar(Expression<Func<TEntity, bool>> predicate);
        int SaveChanges();
    }
}