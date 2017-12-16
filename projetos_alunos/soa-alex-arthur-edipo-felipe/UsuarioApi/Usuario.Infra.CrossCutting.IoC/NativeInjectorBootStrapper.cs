using AutoMapper;
using MediatR;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Usuario.Domain.Core.Notifications;
using Usuario.Domain.Entities.Usuarios.Commands;
using Usuario.Domain.Entities.Usuarios.Events;
using Usuario.Domain.Entities.Usuarios.Repository;
using Usuario.Domain.Handlers;
using Usuario.Domain.Interfaces;
using Usuario.Infra.CrossCutting.AspNetFilters;
using Usuario.Infra.Data.Context;
using Usuario.Infra.Data.EventSourcing;
using Usuario.Infra.Data.Repository;
using Usuario.Infra.Data.Repository.EventSourcing;
using Usuario.Infra.Data.UoW;

namespace Usuario.Infra.CrossCutting.IoC
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
            services.AddScoped<INotificationHandler<RegistrarUsuarioCommand>, UsuarioCommandHandler>();
            services.AddScoped<INotificationHandler<AtualizarUsuarioCommand>, UsuarioCommandHandler>();
            services.AddScoped<INotificationHandler<ExcluirUsuarioCommand>, UsuarioCommandHandler>();

            // Domain - Usuarios
            services.AddScoped<INotificationHandler<DomainNotification>, DomainNotificationHandler>();
            services.AddScoped<INotificationHandler<UsuarioRegistradoEvent>, UsuarioEventHandler>();
            services.AddScoped<INotificationHandler<UsuarioAtualizadoEvent>, UsuarioEventHandler>();
            services.AddScoped<INotificationHandler<UsuarioExcluidoEvent>, UsuarioEventHandler>();

            // Infra - Data
            services.AddScoped<IUsuarioRepository, UsuarioRepository>();
            services.AddScoped<IUnitOfWork, UnitOfWork>();
            services.AddScoped<UsuariosContext>();

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
