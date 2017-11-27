package br.com.unialfa.pos.soa.usuarioService.soap.ws.endPoints;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.unialfa.pos.soa.soap.web_service.GetUsuarioByIdRequest;
import br.com.unialfa.pos.soa.soap.web_service.GetUsuarioByIdResponse;
import br.com.unialfa.pos.soa.soap.web_service.GetUsuariosByPartOfNameRequest;
import br.com.unialfa.pos.soa.soap.web_service.GetUsuariosByPartOfNameResponse;
import br.com.unialfa.pos.soa.soap.web_service.ObjectFactory;
//import br.com.unialfa.pos.soa.soap.usuario_web_service.Usuario;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity.Usuario;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.repository.UsuarioRepository;

@Endpoint
public class UsuarioEndPoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioEndPoint.class);

	private static final String NAMESPACE_URI = "http://soa.pos.unialfa.com.br/soap/web-service";

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioEndPoint(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsuariosByPartOfNameRequest")
	@ResponsePayload
	public GetUsuariosByPartOfNameResponse getUsuarios(@RequestPayload GetUsuariosByPartOfNameRequest request) {

		LOGGER.info("Endpoint received person[term={}]", request.getNome());

		ObjectFactory factory = new ObjectFactory();
		GetUsuariosByPartOfNameResponse response = factory.createGetUsuariosByPartOfNameResponse();

		List<Usuario> usuarios = this.usuarioRepository.findByNomeIgnoreCaseContainingOrderByNomeAsc(request.getNome());
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
	public GetUsuarioByIdResponse getUsuario(@RequestPayload GetUsuarioByIdRequest request) {

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
}