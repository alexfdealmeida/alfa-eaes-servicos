using AutoMapper;

namespace Usuario.Services.Api.AutoMapper
{
    /// <summary>
    /// Configuraçao AutoMapper
    /// </summary>
    public class AutoMapperConfiguration
    {
        /// <summary>
        /// Registra mapeamentos
        /// </summary>
        /// <returns></returns>
        public static MapperConfiguration RegisterMappings()
        {
            return new MapperConfiguration(ps =>
            {
                ps.AddProfile(new DomainToViewModelMappingProfile());
                ps.AddProfile(new ViewModelToDomainMappingProfile());
            });
        }
    }
}