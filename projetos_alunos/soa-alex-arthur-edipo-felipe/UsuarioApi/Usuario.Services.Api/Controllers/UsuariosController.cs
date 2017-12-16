using System;
using System.Collections.Generic;
using AutoMapper;
using MediatR;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Usuario.Domain.Core.Notifications;
using Usuario.Domain.Entities.Usuarios.Commands;
using Usuario.Domain.Entities.Usuarios.Repository;
using Usuario.Domain.Interfaces;
using Usuario.Services.Api.ViewModels;

namespace Usuario.Services.Api.Controllers
{
    /// <summary>
    /// Controller de Usuários
    /// </summary>
    public class UsuariosController : BaseController
    {
        private readonly IUsuarioRepository _usuarioRepository;
        private readonly IMapper _mapper;
        private readonly IMediatorHandler _mediator;

        /// <summary>
        /// Construtor do controller Usuarios
        /// </summary>
        /// <param name="notifications">Passado por injeção de dependência</param>
        /// <param name="usuarioRepository">Passado por injeção de dependência</param>
        /// <param name="mapper">Passado por injeção de dependência</param>
        /// <param name="mediator">Passado por injeção de dependência</param>
        public UsuariosController(INotificationHandler<DomainNotification> notifications, 
                                 IUsuarioRepository usuarioRepository, 
                                 IMapper mapper,
                                 IMediatorHandler mediator) : base(notifications, mediator)
        {
            _usuarioRepository = usuarioRepository;
            _mapper = mapper;
            _mediator = mediator;
        }
        /// <summary>
        /// Obter todos usuários
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("usuarios")]
        [AllowAnonymous]
        public IEnumerable<UsuarioViewModel> Get()
        {
            return _mapper.Map<IEnumerable<UsuarioViewModel>>(_usuarioRepository.ObterTodos());
        }

        /// <summary>
        /// Obter usuário por ID
        /// </summary>
        /// <param name="id">Campo chave</param>
        /// <returns></returns>
        [HttpGet]
        [AllowAnonymous]
        [Route("usuarios/{id:long}")]
        public UsuarioViewModel Get(long id)
        {
            return _mapper.Map<UsuarioViewModel>(_usuarioRepository.ObterPorId(id));
        }
        
        /// <summary>
        /// Adicionar usuário
        /// </summary>
        /// <param name="usuarioViewModel">Modelo usuário</param>
        /// <returns></returns>
        [HttpPost]
        [Route("usuarios")]
        public IActionResult Post([FromBody]UsuarioViewModel usuarioViewModel)
        {
            if (!ModelStateValida())
            {
                return Response();
            }

            var usuarioCommand = _mapper.Map<RegistrarUsuarioCommand>(usuarioViewModel);

            _mediator.EnviarComando(usuarioCommand);
            return Response(usuarioCommand);
        }

        /// <summary>
        /// Atualizar usuário
        /// </summary>
        /// <param name="usuarioViewModel">Modelo usuário</param>
        /// <returns></returns>
        [HttpPut]
        [Route("usuarios")]
        public IActionResult Put([FromBody]UsuarioViewModel usuarioViewModel)
        {
            if (!ModelStateValida())
            {
                return Response();
            }

            var usuarioCommand = _mapper.Map<AtualizarUsuarioCommand>(usuarioViewModel);

            _mediator.EnviarComando(usuarioCommand);
            return Response(usuarioCommand);
        }

        /// <summary>
        /// Remover usuário
        /// </summary>
        /// <param name="id">Campo chave</param>
        /// <returns></returns>
        [HttpDelete]
        [Route("usuarios/{id:long}")]
        public IActionResult Delete(long id)
        {
            var usuarioViewModel = new UsuarioViewModel { Id = id };
            var usuarioCommand = _mapper.Map<ExcluirUsuarioCommand>(usuarioViewModel);

            _mediator.EnviarComando(usuarioCommand);
            return Response(usuarioCommand);
        }

        private bool ModelStateValida()
        {
            if (ModelState.IsValid) return true;

            NotificarErroModelInvalida();
            return false;
        }
    }
}
