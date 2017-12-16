using System;
using System.Collections.Generic;
using AutoMapper;
using MediatR;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Tarefa.Domain.Core.Notifications;
using Tarefa.Domain.Entities.Tarefa.Commands;
using Tarefa.Domain.Entities.Tarefa.Repository;
using Tarefa.Domain.Interfaces;
using Tarefa.Services.Api.ViewModels;

namespace Tarefa.Services.Api.Controllers
{
    public class TarefasController : BaseController
    {
        private readonly ITarefaRepository _tarefaRepository;
        private readonly IMapper _mapper;
        private readonly IMediatorHandler _mediator;

        public TarefasController(INotificationHandler<DomainNotification> notifications, 
                                 ITarefaRepository tarefaRepository, 
                                 IMapper mapper,
                                 IMediatorHandler mediator) : base(notifications, mediator)
        {
            _tarefaRepository = tarefaRepository;
            _mapper = mapper;
            _mediator = mediator;
        }

        [HttpGet]
        [Route("tarefas")]
        [AllowAnonymous]
        public IEnumerable<TarefaViewModel> Get()
        {
            return _mapper.Map<IEnumerable<TarefaViewModel>>(_tarefaRepository.ObterTodos());
        }

        [HttpGet]
        [AllowAnonymous]
        [Route("tarefas/{id:long}")]
        public TarefaViewModel Get(long id)
        {
            return _mapper.Map<TarefaViewModel>(_tarefaRepository.ObterPorId(id));
        }
        
        [HttpPost]
        [Route("tarefas")]
        public IActionResult Post([FromBody]TarefaViewModel tarefaViewModel)
        {
            if (!ModelStateValida())
            {
                return Response();
            }

            var tarefaCommand = _mapper.Map<RegistrarTarefaCommand>(tarefaViewModel);

            _mediator.EnviarComando(tarefaCommand);
            return Response(tarefaCommand);
        }

        [HttpPut]
        [Route("tarefas")]
        public IActionResult Put([FromBody]TarefaViewModel tarefaViewModel)
        {
            if (!ModelStateValida())
            {
                return Response();
            }

            var tarefaCommand = _mapper.Map<AtualizarTarefaCommand>(tarefaViewModel);

            _mediator.EnviarComando(tarefaCommand);
            return Response(tarefaCommand);
        }

        [HttpDelete]
        [Route("tarefas/{id:long}")]
        public IActionResult Delete(long id)
        {
            var tarefaViewModel = new TarefaViewModel { Id = id };
            var tarefaCommand = _mapper.Map<ExcluirTarefaCommand>(tarefaViewModel);

            _mediator.EnviarComando(tarefaCommand);
            return Response(tarefaCommand);
        }

        private bool ModelStateValida()
        {
            if (ModelState.IsValid) return true;

            NotificarErroModelInvalida();
            return false;
        }
    }
}
