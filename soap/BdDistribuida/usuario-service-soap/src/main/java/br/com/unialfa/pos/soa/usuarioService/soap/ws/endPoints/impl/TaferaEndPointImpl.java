package br.com.unialfa.pos.soa.usuarioService.soap.ws.endPoints.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.unialfa.pos.soa.soap.web_service.AlocaTarefaAoUsuarioRequest;
import br.com.unialfa.pos.soa.soap.web_service.AlocaTarefaAoUsuarioResponse;
import br.com.unialfa.pos.soa.soap.web_service.DesalocaAllTarefasDoUsuarioRequest;
import br.com.unialfa.pos.soa.soap.web_service.DesalocaAllTarefasDoUsuarioResponse;
import br.com.unialfa.pos.soa.soap.web_service.DesalocaTarefaDeTodosOsUsuariosRequest;
import br.com.unialfa.pos.soa.soap.web_service.DesalocaTarefaDeTodosOsUsuariosResponse;
import br.com.unialfa.pos.soa.soap.web_service.DesalocaTarefaDeUsuarioEspecificoRequest;
import br.com.unialfa.pos.soa.soap.web_service.DesalocaTarefaDeUsuarioEspecificoResponse;
import br.com.unialfa.pos.soa.soap.web_service.GetTarefaByUsuarioTarefaRemotoIdRequest;
import br.com.unialfa.pos.soa.soap.web_service.GetTarefaByUsuarioTarefaRemotoIdResponse;
import br.com.unialfa.pos.soa.soap.web_service.GetUsuariosDeUmaTarefaByTarefaIdRequest;
import br.com.unialfa.pos.soa.soap.web_service.GetUsuariosDeUmaTarefaByTarefaIdResponse;
import br.com.unialfa.pos.soa.soap.web_service.ObjectFactory;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity.Usuario;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity.UsuarioTarefa;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.repository.UsuarioRepository;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.repository.UsuarioTarefaRepository;
import br.com.unialfa.pos.soa.usuarioService.soap.ws.endPoints.TarefaEndPoint;

@Endpoint
public class TaferaEndPointImpl implements TarefaEndPoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaferaEndPointImpl.class);

	private static final String NAMESPACE_URI = "http://soa.pos.unialfa.com.br/soap/web-service";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioTarefaRepository usuarioTarefaRepository;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsuariosDeUmaTarefaByTarefaIdRequest")
	@ResponsePayload
	@Override
	public GetUsuariosDeUmaTarefaByTarefaIdResponse getUsuariosDeUmaTarefaByTarefaId(
			@RequestPayload GetUsuariosDeUmaTarefaByTarefaIdRequest request) {

		LOGGER.info("Endpoint received person[term={}]", request.getId());

		ObjectFactory factory = new ObjectFactory();
		GetUsuariosDeUmaTarefaByTarefaIdResponse response = factory.createGetUsuariosDeUmaTarefaByTarefaIdResponse();

		List<UsuarioTarefa> uts = this.usuarioTarefaRepository.findByIdTarefaRemotaOrderByUsuarioNome(request.getId());

		List<br.com.unialfa.pos.soa.soap.web_service.UsuarioTarefa> utSoaps = response.getUsuariosTarefas();
		uts.forEach(ut -> {
			br.com.unialfa.pos.soa.soap.web_service.UsuarioTarefa utSoap = factory.createUsuarioTarefa();
			br.com.unialfa.pos.soa.soap.web_service.Usuario uSoap = factory.createUsuario();
			uSoap.setId(ut.getUsuario().getId());
			uSoap.setNome(ut.getUsuario().getNome());
			uSoap.setEmail(ut.getUsuario().getEmail());

			utSoap.setUsuario(uSoap);

			utSoap.setIdUsuarioTarefa(ut.getId());

			utSoaps.add(utSoap);
		});

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "alocaTarefaAoUsuarioRequest")
	@ResponsePayload
	@Override
	public AlocaTarefaAoUsuarioResponse alocaTarefaAoUsuario(@RequestPayload AlocaTarefaAoUsuarioRequest request) {

		LOGGER.info("Endpoint recebeu solicitação para alocar uma tarefa para o usuário [term={}]",
				request.getUsuarioId());

		ObjectFactory factory = new ObjectFactory();
		AlocaTarefaAoUsuarioResponse response = factory.createAlocaTarefaAoUsuarioResponse();

		Usuario u = this.usuarioRepository.findOne(request.getUsuarioId());
		UsuarioTarefa usuarioTarefa = new UsuarioTarefa();
		usuarioTarefa.setUsuario(u);
		usuarioTarefa.setIdTarefaRemota(request.getTarefaId());

		this.usuarioTarefaRepository.save(usuarioTarefa);

		response.setSucesso(true);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTarefaByUsuarioTarefaRemotoIdRequest")
	@ResponsePayload
	@Override
	public GetTarefaByUsuarioTarefaRemotoIdResponse getTarefaByUsuarioTarefaRemotoId(
			@RequestPayload GetTarefaByUsuarioTarefaRemotoIdRequest request) {

		LOGGER.info("Endpoint recebeu solicitacao de tarefa da alocação: [term={}]",
				request.getUsuarioTarefaRemotoId());

		ObjectFactory factory = new ObjectFactory();
		GetTarefaByUsuarioTarefaRemotoIdResponse response = factory.createGetTarefaByUsuarioTarefaRemotoIdResponse();

		UsuarioTarefa ut = this.usuarioTarefaRepository.findOne(request.getUsuarioTarefaRemotoId());

		response.setTarefaId(ut.getIdTarefaRemota());

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "desalocaAllTarefasDoUsuarioRequest")
	@ResponsePayload
	@Override
	public DesalocaAllTarefasDoUsuarioResponse desalocaAllTarefasDoUsuario(
			@RequestPayload DesalocaAllTarefasDoUsuarioRequest request) {

		LOGGER.info("Endpoint recebeu solicitação de desalocar todas as tarefas do usuário [term={}]",
				request.getUsuarioId());

		ObjectFactory factory = new ObjectFactory();
		DesalocaAllTarefasDoUsuarioResponse response = factory.createDesalocaAllTarefasDoUsuarioResponse();

		List<UsuarioTarefa> uts = this.usuarioTarefaRepository.findByUsuarioId(request.getUsuarioId());

		this.usuarioTarefaRepository.delete(uts);

		response.setSucesso(true);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "desalocaTarefaDeUsuarioEspecificoRequest")
	@ResponsePayload
	@Override
	public DesalocaTarefaDeUsuarioEspecificoResponse desalocaTarefaDeUsuarioEspecifico(
			@RequestPayload DesalocaTarefaDeUsuarioEspecificoRequest request) {

		LOGGER.info("Endpoint recebeu solicitação de desalocar uma tarefa de um usuário");

		ObjectFactory factory = new ObjectFactory();
		DesalocaTarefaDeUsuarioEspecificoResponse response = factory.createDesalocaTarefaDeUsuarioEspecificoResponse();

		this.usuarioTarefaRepository.delete(request.getUsuarioTarefaRemotaId());

		response.setSucesso(true);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "desalocaTarefaDeTodosOsUsuariosRequest")
	@ResponsePayload
	@Override
	public DesalocaTarefaDeTodosOsUsuariosResponse desalocaTarefaDeTodosOsUsuarios(
			@RequestPayload DesalocaTarefaDeTodosOsUsuariosRequest request) {

		LOGGER.info("Endpoint recebeu solicitação de desalocar uma tarefa de um usuário");

		ObjectFactory factory = new ObjectFactory();
		DesalocaTarefaDeTodosOsUsuariosResponse response = factory.createDesalocaTarefaDeTodosOsUsuariosResponse();

		List<UsuarioTarefa> uts = this.usuarioTarefaRepository.findByIdTarefaRemota(request.getTarefaRemotaId());

		this.usuarioTarefaRepository.delete(uts);

		response.setSucesso(true);

		return response;
	}

}