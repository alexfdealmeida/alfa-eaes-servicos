using System.Linq;
using MediatR;
using Microsoft.AspNetCore.Mvc;
using Usuario.Domain.Core.Notifications;
using Usuario.Domain.Interfaces;

namespace Usuario.Services.Api.Controllers
{
    /// <summary>
    /// Controlador padr�o, com valida��es padr�es
    /// </summary>
    [Produces("application/json")]
    public abstract class BaseController : Controller
    {
        private readonly DomainNotificationHandler _notifications;
        private readonly IMediatorHandler _mediator;

        /// <summary>
        /// Construtor
        /// </summary>
        /// <param name="notifications">Passado por inje��o de depend�ncia</param>
        /// <param name="mediator">Passado por inje��o de depend�ncia</param>
        protected BaseController(INotificationHandler<DomainNotification> notifications, 
                                 IMediatorHandler mediator)
        {
            _notifications = (DomainNotificationHandler)notifications;
            _mediator = mediator;
        }
        /// <summary>
        /// Resposta de uma requisi�ao
        /// </summary>
        /// <param name="result"></param>
        /// <returns></returns>
        protected new IActionResult Response(object result = null)
        {
            if (OperacaoValida())
            {
                return Ok(new
                {
                    success = true,
                    data = result
                });
            }

            return BadRequest(new
            {
                success = false,
                errors = _notifications.GetNotifications().Select(n=>n.Value)
            });
        }
        /// <summary>
        /// Verifica se existe alguma notifica��o
        /// </summary>
        /// <returns></returns>
        protected bool OperacaoValida()
        {
            return (!_notifications.HasNotifications());
        }

        /// <summary>
        /// Erro de model inv�lida
        /// </summary>
        protected void NotificarErroModelInvalida()
        {
            var erros = ModelState.Values.SelectMany(v => v.Errors);
            foreach (var erro in erros)
            {
                var erroMsg = erro.Exception == null ? erro.ErrorMessage : erro.Exception.Message;
                NotificarErro(string.Empty, erroMsg);
            }
        }

        /// <summary>
        /// Publica erro capturado
        /// </summary>
        /// <param name="codigo">C�digo do erro</param>
        /// <param name="mensagem">Mensagem de erro</param>
        protected void NotificarErro(string codigo, string mensagem)
        {
            _mediator.PublicarUsuario(new DomainNotification(codigo, mensagem));
        }
    }
}