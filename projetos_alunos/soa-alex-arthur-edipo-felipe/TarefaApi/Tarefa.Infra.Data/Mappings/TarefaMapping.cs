using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Tarefa.Infra.Data.Extensions;

namespace Tarefa.Infra.Data.Mappings
{
    public class TarefaMapping : EntityTypeConfiguration<Domain.Entities.Tarefa.Tarefa>
    {
        public override void Map(EntityTypeBuilder<Domain.Entities.Tarefa.Tarefa> builder)
        {
            builder.HasKey(e => e.Id);
            builder.Property(e => e.Titulo)
               .HasColumnType("varchar(255)")
               .IsRequired();

            builder.Property(e => e.Descricao)
                .HasColumnType("varchar(255)");

            builder.Property(e => e.Inicio).HasColumnName("data_inicio");
            builder.Property(e => e.Encerramento).HasColumnName("data_fim");

            builder.Ignore(e => e.ValidationResult);

            builder.Ignore(e => e.CascadeMode);
        }
    }
}