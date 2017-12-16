using System.IO;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Usuario.Domain.Entities.Usuarios;
using Usuario.Infra.Data.Extensions;
using Usuario.Infra.Data.Mappings;

namespace Usuario.Infra.Data.Context
{
    public class UsuariosContext : DbContext
    {
        public DbSet<Domain.Entities.Usuarios.Usuario> Usuarios { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.AddConfiguration(new UsuarioMapping());

            base.OnModelCreating(modelBuilder);
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            var config = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsettings.json")
                .Build();

            optionsBuilder.UseNpgsql(config.GetConnectionString("NetworkConnection"));
        }
    }
}