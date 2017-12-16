using System;
using System.Collections.Generic;
using System.Linq;
using Dapper;
using Microsoft.EntityFrameworkCore;
using Tarefa.Domain.Entities.Tarefa.Repository;
using Tarefa.Infra.Data.Context;

namespace Tarefa.Infra.Data.Repository
{
    public class TarefaRepository : Repository<Domain.Entities.Tarefa.Tarefa>, ITarefaRepository
    {
        public TarefaRepository(TarefasContext context) : base(context)
        {
            
        }
    }
}