using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Usuario.Domain.Core.Events;
using Usuario.Infra.Data.Extensions;

namespace Usuario.Infra.Data.Mappings
{
    public class StoredEventMap : EntityTypeConfiguration<StoredEvent>
    {
        public override void Map(EntityTypeBuilder<StoredEvent> builder)
        {
            builder.Property(c => c.Timestamp)
                .HasColumnName("CreationDate");

            builder.Property(c => c.MessageType)
                .HasColumnName("Action")
                .HasColumnType("varchar(100)");

            builder.ToTable("storedevent");
        }
    }
}