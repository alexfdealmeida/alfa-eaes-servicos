package br.com.unialfa.pos.soa.usuarioService.soap.ws.endPoints;

import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import br.com.unialfa.pos.soa.soap.web_service.GetAllUsuariosRequest;
import br.com.unialfa.pos.soa.soap.web_service.GetAllUsuariosResponse;
import br.com.unialfa.pos.soa.soap.web_service.GetUsuarioByIdRequest;
import br.com.unialfa.pos.soa.soap.web_service.GetUsuarioByIdResponse;
import br.com.unialfa.pos.soa.soap.web_service.RemoveUsuarioRequest;
import br.com.unialfa.pos.soa.soap.web_service.RemoveUsuarioResponse;
import br.com.unialfa.pos.soa.soap.web_service.SaveUsuarioRequest;
import br.com.unialfa.pos.soa.soap.web_service.SaveUsuarioResponse;

public interface UsuarioEndPoint {

	public GetAllUsuariosResponse getUsuarios(@RequestPayload GetAllUsuariosRequest request);

	public GetUsuarioByIdResponse getUsuarioById(@RequestPayload GetUsuarioByIdRequest request);

	public SaveUsuarioResponse saveUsuario(@RequestPayload SaveUsuarioRequest request);

	public RemoveUsuarioResponse removeUsuario(@RequestPayload RemoveUsuarioRequest request);
}