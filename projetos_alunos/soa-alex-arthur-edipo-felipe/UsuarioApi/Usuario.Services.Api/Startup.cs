using System;
using System.IO;
using AutoMapper;
using Elmah.Io.AspNetCore;
using Elmah.Io.Extensions.Logging;
using MediatR;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Formatters;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.PlatformAbstractions;
using Swashbuckle.AspNetCore.Swagger;
using Usuario.Infra.CrossCutting.AspNetFilters;
using Usuario.Infra.CrossCutting.IoC;

namespace Usuario.Services.Api
{
    /// <summary>
    /// Configurações ao iniciar
    /// </summary>
    public class Startup
    {
        /// <summary>
        /// Construtor
        /// </summary>
        /// <param name="env"></param>
        public Startup(IHostingEnvironment env)
        {
            var builder = new ConfigurationBuilder()
                .SetBasePath(env.ContentRootPath)
                .AddJsonFile("appsettings.json", false, true)
                .AddJsonFile($"appsettings.{env.EnvironmentName}.json", true)
                .AddEnvironmentVariables();
            Configuration = builder.Build();
        }
        /// <summary>
        /// Interface de Configuração
        /// </summary>
        public IConfigurationRoot Configuration { get; }

        /// <summary>
        /// Configurar serviços
        /// </summary>
        /// <param name="services"></param>
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddOptions();
            services.AddMvc(options =>
            {
                options.OutputFormatters.Remove(new XmlDataContractSerializerOutputFormatter());
                options.Filters.Add(new ServiceFilterAttribute(typeof(GlobalActionLogger)));
            });

            services.AddAutoMapper();
            services.AddSwaggerGen(s =>
            {
                s.SwaggerDoc("v1", new Info
                {
                    Version = "v1",
                    Title = "Usuarios API",
                    Description = "Desenvolvido para a disciplina de Arquiteturas Orientadas a Serviços - UNIALFA",
                    TermsOfService = "Nenhum",
                    Contact = new Contact() { Name = "Alex Ferreira, Arthur Caetano, Édipo Campos, Felipe Saboya"}
                });
                var basePath = PlatformServices.Default.Application.ApplicationBasePath;
                var xmlPath = Path.Combine(basePath, "Usuario.Services.Api.xml");
                s.IncludeXmlComments(xmlPath);
            });

            services.AddMediatR(typeof(Startup));

            // Registrar todos os DI
            RegisterServices(services);
        }

        /// <summary>
        /// Configurar aplicacação
        /// </summary>
        /// <param name="app">Aplicação</param>
        /// <param name="env">Define o ambiente (Desenvolvimento ou Produção)</param>
        /// <param name="loggerFactory">Monitora erros da aplicação para o ElmahIo</param>
        /// <param name="accessor"></param>
        public void Configure(IApplicationBuilder app,
            IHostingEnvironment env,
            ILoggerFactory loggerFactory,
            IHttpContextAccessor accessor)
        {
            loggerFactory.AddConsole(Configuration.GetSection("Logging"));
            loggerFactory.AddDebug();

            var elmahSts = new ElmahIoSettings
            {
                OnMessage = message =>
                {
                    message.Version = "v1.0";
                    message.Application = "UsuariosAPI";
                    message.User = "Servidor";
                },
            };

            loggerFactory.AddElmahIo("e1ce5cbd905b42538c649f6e1d66351e", new Guid("19ad15fd-5158-4b7a-b36d-ab56dfe4500a"));
            app.UseElmahIo("56fe8edb8db84a22a619c17c265bd343", new Guid("7c905d1b-ca05-426c-81f4-84cbc3eca44f"), elmahSts);

            app.UseCors(c =>
            {
                c.AllowAnyHeader();
                c.AllowAnyMethod();
                c.AllowAnyOrigin();
            });

            app.UseStaticFiles();
            app.UseMvc();
            app.UseSwagger();
            app.UseSwaggerUI(s =>
            {
                s.SwaggerEndpoint("/swagger/v1/swagger.json", "UsuariosEP.IO API v1.0");
            });
        }

        private static void RegisterServices(IServiceCollection services)
        {
            NativeInjectorBootStrapper.RegisterServices(services);
        }
    }
}
