using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;

namespace Usuario.Services.Api.Middlewares
{
    /// <summary>
    /// Middleware Custom do Swagger
    /// </summary>
    public class SwaggerMiddleware
    {
        private readonly RequestDelegate _next;

        /// <summary>
        /// Construtor
        /// </summary>
        /// <param name="next"></param>
        public SwaggerMiddleware(RequestDelegate next)
        {
            _next = next;
        }

        /// <summary>
        /// Chamada
        /// </summary>
        /// <param name="context"></param>
        /// <returns></returns>
        public async Task Invoke(HttpContext context)
        {
            if(context.Request.Path.StartsWithSegments("/swagger"))
            {
                context.Response.StatusCode = StatusCodes.Status404NotFound;
                return;
            }

            await _next.Invoke(context);
        }
    }

    /// <summary>
    /// Extensão do Swagger Middleware
    /// </summary>
    public static class SwaggerMiddlewareExtensions
    {
        /// <summary>
        /// Utilizar Login Swagger
        /// </summary>
        /// <param name="builder"></param>
        /// <returns></returns>
        public static IApplicationBuilder UseSwaggerAuthorized(this IApplicationBuilder builder)
        {
            return builder.UseMiddleware<SwaggerMiddleware>();
        }
    }
}