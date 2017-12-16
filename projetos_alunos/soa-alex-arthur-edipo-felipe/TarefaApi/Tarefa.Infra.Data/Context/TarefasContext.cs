using System.IO;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Tarefa.Infra.Data.Extensions;
using Tarefa.Infra.Data.Mappings;

namespace Tarefa.Infra.Data.Context
{
    public class TarefasContext : DbContext
    {
        public DbSet<Domain.Entities.Tarefa.Tarefa> Tarefas { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.AddConfiguration(new TarefaMapping());

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