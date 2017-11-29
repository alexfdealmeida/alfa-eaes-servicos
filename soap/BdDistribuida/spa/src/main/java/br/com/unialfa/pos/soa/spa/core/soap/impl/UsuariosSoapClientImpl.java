package br.com.unialfa.pos.soa.spa.core.soap.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import br.com.unialfa.pos.soa.spa.core.soap.UsuariosSoapClient;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTarefaTo;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTo;
import br.com.unialfa.pos.soa.spa.helper.Convert;
import servicos.wsdl.AlocaTarefaAoUsuarioRequest;
import servicos.wsdl.AlocaTarefaAoUsuarioResponse;
import servicos.wsdl.DesalocaTarefaDeTodosOsUsuariosRequest;
import servicos.wsdl.DesalocaTarefaDeUsuarioEspecificoRequest;
import servicos.wsdl.GetAllUsuariosRequest;
import servicos.wsdl.GetAllUsuariosResponse;
import servicos.wsdl.GetTarefaByUsuarioTarefaRemotoIdRequest;
import servicos.wsdl.GetTarefaByUsuarioTarefaRemotoIdResponse;
import servicos.wsdl.GetUsuarioByIdRequest;
import servicos.wsdl.GetUsuarioByIdResponse;
import servicos.wsdl.GetUsuariosDeUmaTarefaByTarefaIdRequest;
import servicos.wsdl.GetUsuariosDeUmaTarefaByTarefaIdResponse;
import servicos.wsdl.ObjectFactory;
import servicos.wsdl.RemoveUsuarioRequest;
import servicos.wsdl.SaveUsuarioRequest;
import servicos.wsdl.SaveUsuarioResponse;

@Component
public class UsuariosSoapClientImpl extends WebServiceGatewaySupport implements UsuariosSoapClient {

	private static final String WS_SERVICE_URI = "http://localhost:9000/ws/service";
	private static final Logger log = LoggerFactory.getLogger(UsuariosSoapClientImpl.class);

	@Override
	public UsuarioTo findOne(Long id) {

		ObjectFactory factory = new ObjectFactory();

		GetUsuarioByIdRequest request = factory.createGetUsuarioByIdRequest();
		request.setId(id);

		log.info("Looking for user whose id is " + id);

		GetUsuarioByIdResponse response = (GetUsuarioByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				WS_SERVICE_URI, request, new SoapActionCallback(WS_SERVICE_URI + "/GetUsuarioById"));

		UsuarioTo usuario = new UsuarioTo();
		if (response.getUsuario() != null) {
			usuario.setId(response.getUsuario().getId());
			usuario.setEmail(response.getUsuario().getEmail());
			usuario.setNome(response.getUsuario().getNome());
		}

		return usuario;
	}

	@Override
	public List<UsuarioTo> findByOrderByNomeAsc() {
		ObjectFactory factory = new ObjectFactory();

		GetAllUsuariosRequest request = factory.createGetAllUsuariosRequest();

		log.info("Solicitando todos os usuários");

		GetAllUsuariosResponse response = (GetAllUsuariosResponse) getWebServiceTemplate().marshalSendAndReceive(
				WS_SERVICE_URI, request, new SoapActionCallback(WS_SERVICE_URI + "/GetAllUsuarios"));

		List<UsuarioTo> tos = new ArrayList<>();

		if (response.getUsuarios() != null) {
			response.getUsuarios().forEach(u -> {
				UsuarioTo usuarioTo = new UsuarioTo(u);
				tos.add(usuarioTo);
			});
		}

		return tos;
	}

	@Override
	public List<UsuarioTarefaTo> findByTarefaIdOrderByUsuarioNome(Long idTarefa) {
		ObjectFactory factory = new ObjectFactory();

		GetUsuariosDeUmaTarefaByTarefaIdRequest request = factory.createGetUsuariosDeUmaTarefaByTarefaIdRequest();
		request.setId(idTarefa);

		log.info("Solicitando todos os usuários");

		GetUsuariosDeUmaTarefaByTarefaIdResponse response = (GetUsuariosDeUmaTarefaByTarefaIdResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WS_SERVICE_URI, request,
						new SoapActionCallback(WS_SERVICE_URI + "/GetUsuariosDeUmaTarefaByTarefaId"));

		List<UsuarioTarefaTo> tos = new ArrayList<>();

		if (response.getUsuariosTarefas() != null) {
			response.getUsuariosTarefas().forEach(ut -> {
				UsuarioTo usuarioTo = new UsuarioTo(ut.getUsuario());
				UsuarioTarefaTo usuarioTarefaTo = new UsuarioTarefaTo();
				usuarioTarefaTo.setIdUsuarioTarefaRemota(ut.getIdUsuarioTarefa());
				usuarioTarefaTo.setIdTarefa(idTarefa);
				usuarioTarefaTo.setUsuarioTo(usuarioTo);
				tos.add(usuarioTarefaTo);
			});
		}

		tos.sort((a, b) -> a.getUsuarioTo().getNome().compareTo(b.getUsuarioTo().getNome()));

		return tos;
	}

	@Override
	public UsuarioTo saveUsuario(UsuarioTo to) {
		ObjectFactory factory = new ObjectFactory();

		SaveUsuarioRequest request = factory.createSaveUsuarioRequest();
		request.setUsuario(Convert.usuarioToToUsuarioService(to));

		log.info("Solicitando gravação de usuário remoto");

		SaveUsuarioResponse response = (SaveUsuarioResponse) getWebServiceTemplate().marshalSendAndReceive(
				WS_SERVICE_URI, request, new SoapActionCallback(WS_SERVICE_URI + "/SaveUsuario"));

		return Convert.usuarioServiceToUsuarioTo(response.getUsuario());
	}

	@Override
	public void removeUsuario(Long usuarioRemotoId) {
		ObjectFactory factory = new ObjectFactory();

		RemoveUsuarioRequest request = factory.createRemoveUsuarioRequest();
		request.setUsuarioId(usuarioRemotoId);

		log.info("Solicitando remoção de usuário remoto: [term={}]", usuarioRemotoId);

		getWebServiceTemplate().marshalSendAndReceive(WS_SERVICE_URI, request,
				new SoapActionCallback(WS_SERVICE_URI + "/RemoveUsuario"));

	}

	@Override
	public void removeTarefaDeTodosOsUsuarios(Long tarefaId) {
		ObjectFactory factory = new ObjectFactory();

		DesalocaTarefaDeTodosOsUsuariosRequest request = factory.createDesalocaTarefaDeTodosOsUsuariosRequest();

		log.info("Solicitando gravação de usuário remoto");

		getWebServiceTemplate().marshalSendAndReceive(WS_SERVICE_URI, request,
				new SoapActionCallback(WS_SERVICE_URI + "/DesalocaTarefaDeTodosOsUsuarios"));

	}

	@Override
	public Boolean alocaTarefaParaUsuario(Long tarefaId, Long usuarioId) {
		ObjectFactory factory = new ObjectFactory();

		AlocaTarefaAoUsuarioRequest request = factory.createAlocaTarefaAoUsuarioRequest();
		request.setTarefaId(tarefaId);
		request.setUsuarioId(usuarioId);

		log.info("Solicitando alocação de tarefa a usuário remoto");

		AlocaTarefaAoUsuarioResponse response = (AlocaTarefaAoUsuarioResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WS_SERVICE_URI, request,
						new SoapActionCallback(WS_SERVICE_URI + "/AlocaTarefaAoUsuario"));

		return response.isSucesso();

	}

	@Override
	public Long desalocaTarefaParaUsuario(Long usuarioTarefaId) {

//		 obtém o id da tarefa antes de apagar
		Long idTarefa = this.obtemIdDeTarefaDeUmaAlocacao(usuarioTarefaId);

		ObjectFactory factory = new ObjectFactory();

		DesalocaTarefaDeUsuarioEspecificoRequest request = factory.createDesalocaTarefaDeUsuarioEspecificoRequest();
		request.setUsuarioTarefaRemotaId(usuarioTarefaId);

		log.info("Solicitando a desalocação de tarefa a usuário remoto");

		getWebServiceTemplate().marshalSendAndReceive(WS_SERVICE_URI, request,
				new SoapActionCallback(WS_SERVICE_URI + "/DesalocaTarefaDeUsuarioEspecifico"));

		return idTarefa;
	}

	@Override
	public Long obtemIdDeTarefaDeUmaAlocacao(Long usuarioTarefaId) {
		ObjectFactory factory = new ObjectFactory();

		GetTarefaByUsuarioTarefaRemotoIdRequest request = factory.createGetTarefaByUsuarioTarefaRemotoIdRequest();
		request.setUsuarioTarefaRemotoId(usuarioTarefaId);

		log.info("Solicita " + usuarioTarefaId);

		GetTarefaByUsuarioTarefaRemotoIdResponse response = (GetTarefaByUsuarioTarefaRemotoIdResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WS_SERVICE_URI, request,
						new SoapActionCallback(WS_SERVICE_URI + "/GetTarefaByUsuarioTarefaRemotoId"));

		return response.getTarefaId();
	}

}
