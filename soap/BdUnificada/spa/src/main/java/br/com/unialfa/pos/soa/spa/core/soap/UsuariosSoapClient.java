package br.com.unialfa.pos.soa.spa.core.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import br.com.unialfa.pos.soa.spa.core.model.entity.Usuario;
import servicos.wsdl.GetUsuarioByIdRequest;
import servicos.wsdl.GetUsuarioByIdResponse;
import servicos.wsdl.GetUsuariosByPartOfNameRequest;
import servicos.wsdl.GetUsuariosByPartOfNameResponse;
import servicos.wsdl.ObjectFactory;

@Component
public class UsuariosSoapClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(UsuariosSoapClient.class);

	public GetUsuariosByPartOfNameResponse getUsuariosByPartOfName(String nomeOuParteDoNome) {

		GetUsuariosByPartOfNameRequest request = new GetUsuariosByPartOfNameRequest();
		request.setNome(nomeOuParteDoNome);

		log.info("Looking for users containing " + nomeOuParteDoNome);

		GetUsuariosByPartOfNameResponse response = (GetUsuariosByPartOfNameResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:9000/ws/usuarios", request,
						new SoapActionCallback("http://localhost:9000/ws/usuarios/GetUsuariosByPartOfName"));

		return response;
	}

	@Transactional
	public Usuario findOne(Long id) {

		ObjectFactory factory = new ObjectFactory();

		GetUsuarioByIdRequest request = factory.createGetUsuarioByIdRequest();
		request.setId(id);

		log.info("Looking for user whose id is " + id);

		GetUsuarioByIdResponse response = (GetUsuarioByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://localhost:9000/ws/usuarios", request,
				new SoapActionCallback("http://localhost:9000/ws/usuarios/GetUsuarioById"));

		Usuario usuario = new Usuario();
		if (response.getUsuario() != null) {
			usuario.setId(response.getUsuario().getId());
			usuario.setEmail(response.getUsuario().getEmail());
			usuario.setNome(response.getUsuario().getNome());
		}

		return usuario;
	}

}
