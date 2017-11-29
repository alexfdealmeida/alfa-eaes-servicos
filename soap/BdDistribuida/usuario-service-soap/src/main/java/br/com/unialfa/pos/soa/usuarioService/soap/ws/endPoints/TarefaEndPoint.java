package br.com.unialfa.pos.soa.usuarioService.soap.ws.endPoints;

import org.springframework.ws.server.endpoint.annotation.RequestPayload;

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

public interface TarefaEndPoint {
	public GetUsuariosDeUmaTarefaByTarefaIdResponse getUsuariosDeUmaTarefaByTarefaId(
			@RequestPayload GetUsuariosDeUmaTarefaByTarefaIdRequest request);

	public GetTarefaByUsuarioTarefaRemotoIdResponse getTarefaByUsuarioTarefaRemotoId(
			@RequestPayload GetTarefaByUsuarioTarefaRemotoIdRequest request);

	public AlocaTarefaAoUsuarioResponse alocaTarefaAoUsuario(@RequestPayload AlocaTarefaAoUsuarioRequest request);

	public DesalocaAllTarefasDoUsuarioResponse desalocaAllTarefasDoUsuario(
			@RequestPayload DesalocaAllTarefasDoUsuarioRequest request);

	public DesalocaTarefaDeUsuarioEspecificoResponse desalocaTarefaDeUsuarioEspecifico(
			@RequestPayload DesalocaTarefaDeUsuarioEspecificoRequest request);

	public DesalocaTarefaDeTodosOsUsuariosResponse desalocaTarefaDeTodosOsUsuarios(
			@RequestPayload DesalocaTarefaDeTodosOsUsuariosRequest request);
}
