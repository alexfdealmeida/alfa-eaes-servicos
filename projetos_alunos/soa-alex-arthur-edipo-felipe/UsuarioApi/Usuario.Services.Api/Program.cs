using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;

namespace Usuario.Services.Api
{
    /// <summary>
    /// WEB API
    /// </summary>
    public class Program
    {
        /// <summary>
        /// Inicio da aplicação
        /// </summary>
        /// <param name="args"></param>
        public static void Main(string[] args)
        {
            BuildWebHost(args).Run();
        }

        /// <summary>
        /// Configuração padrão
        /// </summary>
        /// <param name="args"></param>
        /// <returns></returns>
        public static IWebHost BuildWebHost(string[] args) =>
            WebHost.CreateDefaultBuilder(args)
                .UseStartup<Startup>()
                .Build();
    }
}
