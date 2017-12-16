using AutoMapper;
using MediatR;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Tarefa.Domain.Core.Notifications;
using Tarefa.Domain.Entities.Tarefa.Commands;
using Tarefa.Domain.Entities.Tarefa.Events;
using Tarefa.Domain.Entities.Tarefa.Repository;
using Tarefa.Domain.Handlers;
using Tarefa.Domain.Interfaces;
using Tarefa.Infra.CrossCutting.AspNetFilters;
using Tarefa.Infra.Data.Context;
using Tarefa.Infra.Data.EventSourcing;
using Tarefa.Infra.Data.Repository;
using Tarefa.Infra.Data.Repository.EventSourcing;
using Tarefa.Infra.Data.UoW;

namespace Tarefa.Infra.CrossCutting.IoC
{
    public class NativeInjectorBootStrapper
    {
        public static void RegisterServices(IServiceCollection services)
        {
            // ASPNET
            services.AddSingleton<IHttpContextAccessor, HttpContextAccessor>();
            services.AddSingleton(Mapper.Configuration);
            services.AddScoped<IMapper>(sp => new Mapper(sp.GetRequiredService<IConfigurationProvider>(), sp.GetService));

            // Domain Bus (Mediator)
            services.AddScoped<IMediatorHandler, MediatorHandler>();

            // Domain - Commands
            services.AddScoped<INotificationHandler<RegistrarTarefaCommand>, TarefaCommandHandler>();
            services.AddScoped<INotificationHandler<AtualizarTarefaCommand>, TarefaCommandHandler>();
            services.AddScoped<INotificationHandler<ExcluirTarefaCommand>, TarefaCommandHandler>();

            // Domain - Tarefas
            services.AddScoped<INotificationHandler<DomainNotification>, DomainNotificationHandler>();
            services.AddScoped<INotificationHandler<TarefaRegistradoEvent>, TarefaEventHandler>();
            services.AddScoped<INotificationHandler<TarefaAtualizadoEvent>, TarefaEventHandler>();
            services.AddScoped<INotificationHandler<TarefaExcluidoEvent>, TarefaEventHandler>();

            // Infra - Data
            services.AddScoped<ITarefaRepository, TarefaRepository>();
            services.AddScoped<IUnitOfWork, UnitOfWork>();
            services.AddScoped<TarefasContext>();

            // Infra - Data EventSourcing
            services.AddScoped<IEventStoreRepository, EventStoreSQLRepository>();
            services.AddScoped<IEventStore, SqlEventStore>();
            services.AddScoped<EventStoreSQLContext>();

            // Infra - Filtros
            services.AddScoped<ILogger<GlobalExceptionHandlingFilter>, Logger<GlobalExceptionHandlingFilter>>();
            services.AddScoped<ILogger<GlobalActionLogger>, Logger<GlobalActionLogger>>();
            services.AddScoped<GlobalExceptionHandlingFilter>();
            services.AddScoped<GlobalActionLogger>();
        }
    }
}
