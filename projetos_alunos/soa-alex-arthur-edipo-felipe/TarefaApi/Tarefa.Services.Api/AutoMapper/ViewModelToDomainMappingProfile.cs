using System;
using AutoMapper;
using Tarefa.Domain.Entities.Tarefa.Commands;
using Tarefa.Services.Api.ViewModels;

namespace Tarefa.Services.Api.AutoMapper
{
    public class ViewModelToDomainMappingProfile : Profile
    {
        public ViewModelToDomainMappingProfile()
        {
            CreateMap<TarefaViewModel, RegistrarTarefaCommand>()
                .ConstructUsing(c => new RegistrarTarefaCommand(c.Titulo, c.Descricao, c.Inicio, c.Encerramento));

            CreateMap<TarefaViewModel, AtualizarTarefaCommand>()
                .ConstructUsing(c => new AtualizarTarefaCommand(c.Id, c.Titulo, c.Descricao, c.Inicio, c.Encerramento));

            CreateMap<TarefaViewModel, ExcluirTarefaCommand>()
                .ConstructUsing(c => new ExcluirTarefaCommand(c.Id));
        }
    }
}