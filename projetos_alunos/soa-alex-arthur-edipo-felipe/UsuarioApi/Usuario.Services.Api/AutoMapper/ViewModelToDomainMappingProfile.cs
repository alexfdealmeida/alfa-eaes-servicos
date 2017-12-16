using System;
using AutoMapper;
using Usuario.Domain.Entities.Usuarios.Commands;
using Usuario.Services.Api.ViewModels;

namespace Usuario.Services.Api.AutoMapper
{
    /// <summary>
    /// Converte Modelo de visão para Domínio
    /// </summary>
    public class ViewModelToDomainMappingProfile : Profile
    {
        /// <summary>
        /// Mapeamento
        /// </summary>
        public ViewModelToDomainMappingProfile()
        {
            CreateMap<UsuarioViewModel, RegistrarUsuarioCommand>()
                .ConstructUsing(c => new RegistrarUsuarioCommand(c.Nome, c.Email));

            CreateMap<UsuarioViewModel, AtualizarUsuarioCommand>()
                .ConstructUsing(c => new AtualizarUsuarioCommand(c.Id, c.Nome, c.Email));

            CreateMap<UsuarioViewModel, ExcluirUsuarioCommand>()
                .ConstructUsing(c => new ExcluirUsuarioCommand(c.Id));
        }
    }
}