using AutoMapper;
using Usuario.Domain.Entities.Usuarios;
using Usuario.Services.Api.ViewModels;

namespace Usuario.Services.Api.AutoMapper
{
    /// <summary>
    /// Converte Domínio para Modelo de visão
    /// </summary>
    public class DomainToViewModelMappingProfile : Profile
    {
        /// <summary>
        /// Mapeamento
        /// </summary>
        public DomainToViewModelMappingProfile()
        {
            CreateMap<Domain.Entities.Usuarios.Usuario, UsuarioViewModel>();
        }
    }
}