package br.com.unialfa.pos.soa.spa.core.soap;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import br.com.unialfa.pos.soa.spa.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.spa.core.model.entity.Usuario;
import servicos.wsdl.GetAllTarefasRequest;
import servicos.wsdl.GetAllTarefasResponse;
import servicos.wsdl.GetUsuarioByIdRequest;
import servicos.wsdl.GetUsuarioByIdResponse;
import servicos.wsdl.ObjectFactory;
//import servicos.wsdl.Tarefa;

@Component
public class TarefasSoapClient extends WebServiceGatewaySupport {
	@Value("${service.uri}")
	String SERVICE_URI;

	private static final Logger log = LoggerFactory.getLogger(TarefasSoapClient.class);

	public List<Tarefa> getAllTarefas() {

		ObjectFactory factory = new ObjectFactory();

		GetAllTarefasRequest request = factory.createGetAllTarefasRequest();

		log.info("Looking for all tarefas");

		GetAllTarefasResponse response = (GetAllTarefasResponse) getWebServiceTemplate().marshalSendAndReceive(
				this.SERVICE_URI, request, new SoapActionCallback(this.SERVICE_URI + "/GetAllTarefas"));
		
		List<servicos.wsdl.Tarefa> soapTs = response.getTarefas();
		
		List<Tarefa> tarefas = new ArrayList<>();
		
		soapTs.forEach(t -> {
			Tarefa tarefa = new Tarefa();
			tarefa.setId(t.getId());
			tarefa.setTitulo(t.getTitulo());
			tarefa.setDescricao(t.getDescricao());
			tarefa.setDataInicio(t.getDataInicio().toGregorianCalendar().getTime());
			tarefa.setDataFim(t.getDataFim().toGregorianCalendar().getTime());
			
			tarefas.add(tarefa);
		});
		

		return tarefas;
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
