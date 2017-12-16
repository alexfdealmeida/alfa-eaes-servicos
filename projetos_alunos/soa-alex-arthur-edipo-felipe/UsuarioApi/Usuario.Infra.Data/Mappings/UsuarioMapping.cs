using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Usuario.Domain.Entities.Usuarios;
using Usuario.Infra.Data.Extensions;

namespace Usuario.Infra.Data.Mappings
{
    public class UsuarioMapping : EntityTypeConfiguration<Domain.Entities.Usuarios.Usuario>
    {
        public override void Map(EntityTypeBuilder<Domain.Entities.Usuarios.Usuario> builder)
        {
            builder.HasKey(e => e.Id);
            builder.Property(e => e.Nome)
               .HasColumnType("varchar(255)")
               .IsRequired();

            builder.Property(e => e.Email)
                .HasColumnType("varchar(255)");

            builder.Ignore(e => e.ValidationResult);

            builder.Ignore(e => e.CascadeMode);

            builder.ToTable("usuarios");
        }
    }
}