using AutoMapper;
using Tarefa.Services.Api.ViewModels;

namespace Tarefa.Services.Api.AutoMapper
{
    public class DomainToViewModelMappingProfile : Profile
    {
        public DomainToViewModelMappingProfile()
        {
            CreateMap<Domain.Entities.Tarefa.Tarefa, TarefaViewModel>();
        }
    }
}