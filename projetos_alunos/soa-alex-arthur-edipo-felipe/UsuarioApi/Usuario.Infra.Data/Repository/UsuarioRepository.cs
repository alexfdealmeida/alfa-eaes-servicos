using System;
using System.Collections.Generic;
using System.Linq;
using Dapper;
using Microsoft.EntityFrameworkCore;
using Usuario.Domain.Entities.Usuarios;
using Usuario.Domain.Entities.Usuarios.Repository;
using Usuario.Infra.Data.Context;

namespace Usuario.Infra.Data.Repository
{
    public class UsuarioRepository : Repository<Domain.Entities.Usuarios.Usuario>, IUsuarioRepository
    {
        public UsuarioRepository(UsuariosContext context) : base(context)
        {
            
        }
    }
}