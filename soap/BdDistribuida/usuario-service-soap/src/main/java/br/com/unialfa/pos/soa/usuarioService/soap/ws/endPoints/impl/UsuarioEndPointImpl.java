package br.com.unialfa.pos.soa.usuarioService.soap.ws.endPoints.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.unialfa.pos.soa.soap.web_service.GetAllUsuariosRequest;
import br.com.unialfa.pos.soa.soap.web_service.GetAllUsuariosResponse;
import br.com.unialfa.pos.soa.soap.web_service.GetUsuarioByIdRequest;
import br.com.unialfa.pos.soa.soap.web_service.GetUsuarioByIdResponse;
import br.com.unialfa.pos.soa.soap.web_service.ObjectFactory;
import br.com.unialfa.pos.soa.soap.web_service.RemoveUsuarioRequest;
import br.com.unialfa.pos.soa.soap.web_service.RemoveUsuarioResponse;
import br.com.unialfa.pos.soa.soap.web_service.SaveUsuarioRequest;
import br.com.unialfa.pos.soa.soap.web_service.SaveUsuarioResponse;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity.Usuario;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity.UsuarioTarefa;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.repository.UsuarioRepository;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.repository.UsuarioTarefaRepository;
import br.com.unialfa.pos.soa.usuarioService.soap.ws.endPoints.UsuarioEndPoint;
import br.com.unialfa.pos.soa.usuarioService.soap.ws.helper.Convert;

@Endpoint
public class UsuarioEndPointImpl implements UsuarioEndPoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioEndPointImpl.class);

	private static final String NAMESPACE_URI = "http://soa.pos.unialfa.com.br/soap/web-service";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioTarefaRepository usuarioTarefaRepository;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsuariosRequest")
	@ResponsePayload
	@Override
	public GetAllUsuariosResponse getUsuarios(@RequestPayload GetAllUsuariosRequest request) {

		LOGGER.info("Endpoint received all usuarios request");

		ObjectFactory factory = new ObjectFactory();
		GetAllUsuariosResponse response = factory.createGetAllUsuariosResponse();

		List<Usuario> usuarios = this.usuarioRepository.findByOrderByNomeAsc();
		List<br.com.unialfa.pos.soa.soap.web_service.Usuario> uSoaps = response.getUsuarios();
		usuarios.forEach(u -> {
			br.com.unialfa.pos.soa.soap.web_service.Usuario uSoap = factory.createUsuario();
			uSoap.setId(u.getId());
			uSoap.setNome(u.getNome());
			uSoap.setEmail(u.getEmail());

			uSoaps.add(uSoap);
		});

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsuarioByIdRequest")
	@ResponsePayload
	@Override
	public GetUsuarioByIdResponse getUsuarioById(@RequestPayload GetUsuarioByIdRequest request) {

		LOGGER.info("Endpoint received person[term={}]", request.getId());

		ObjectFactory factory = new ObjectFactory();
		GetUsuarioByIdResponse response = factory.createGetUsuarioByIdResponse();

		Usuario usuario = this.usuarioRepository.findOne(request.getId());

		br.com.unialfa.pos.soa.soap.web_service.Usuario uSoap = factory.createUsuario();
		uSoap.setId(usuario.getId());
		uSoap.setNome(usuario.getNome());
		uSoap.setEmail(usuario.getEmail());

		response.setUsuario(uSoap);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveUsuarioRequest")
	@ResponsePayload
	@Override
	public SaveUsuarioResponse saveUsuario(@RequestPayload SaveUsuarioRequest request) {

		LOGGER.info("Endpoint received person[term={}] to save", request.getUsuario().getNome());

		ObjectFactory factory = new ObjectFactory();
		SaveUsuarioResponse response = factory.createSaveUsuarioResponse();

		Usuario u = this.usuarioRepository.save(Convert.ServiceUsuarioToEntityUsuario(request.getUsuario()));

		response.setUsuario(Convert.EntityUsuarioToServiceUsuario(u));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeUsuarioRequest")
	@ResponsePayload
	@Transactional
	@Override
	public RemoveUsuarioResponse removeUsuario(@RequestPayload RemoveUsuarioRequest request) {

		LOGGER.info("Endpoint recebeu usuário para remover: [term={}]", request.getUsuarioId());

		ObjectFactory factory = new ObjectFactory();
		RemoveUsuarioResponse response = factory.createRemoveUsuarioResponse();

		// remove tarefas do usuário
		List<UsuarioTarefa> uts = this.usuarioTarefaRepository.findByUsuarioId(request.getUsuarioId());
		this.usuarioTarefaRepository.delete(uts);

		// remove o usuário
		this.usuarioRepository.delete(request.getUsuarioId());

		return response;
	}

}