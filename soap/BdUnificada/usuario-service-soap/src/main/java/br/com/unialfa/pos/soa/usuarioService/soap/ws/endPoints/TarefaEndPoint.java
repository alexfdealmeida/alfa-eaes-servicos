package br.com.unialfa.pos.soa.usuarioService.soap.ws.endPoints;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.unialfa.pos.soa.soap.web_service.GetAllTarefasRequest;
import br.com.unialfa.pos.soa.soap.web_service.GetAllTarefasResponse;
import br.com.unialfa.pos.soa.soap.web_service.ObjectFactory;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.usuarioService.soap.core.model.repository.TarefaRepository;

@Endpoint
public class TarefaEndPoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(TarefaEndPoint.class);

	private static final String NAMESPACE_URI = "http://soa.pos.unialfa.com.br/soap/web-service";

	private TarefaRepository tarefaRepository;

	@Autowired
	public TarefaEndPoint(TarefaRepository tarefaRepository) {
		this.tarefaRepository = tarefaRepository;
	}
	
	private XMLGregorianCalendar dateToXMLGregorianCalendar(Date data) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(data);
		XMLGregorianCalendar date2 = null;
		try {
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		return date2;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllTarefasRequest")
	@ResponsePayload
	public GetAllTarefasResponse getAllTarefas(@RequestPayload GetAllTarefasRequest request) {

		LOGGER.info("Endpoint received all tarefas request");

		ObjectFactory factory = new ObjectFactory();
		GetAllTarefasResponse response = factory.createGetAllTarefasResponse();

		List<Tarefa> tarefas = this.tarefaRepository.findByOrderByDataInicioDesc();
		List<br.com.unialfa.pos.soa.soap.web_service.Tarefa> uSoaps = response.getTarefas();
		tarefas.forEach(u -> {
			br.com.unialfa.pos.soa.soap.web_service.Tarefa uSoap = factory.createTarefa();
			uSoap.setId(u.getId());
			uSoap.setTitulo(u.getTitulo());
			uSoap.setDescricao(u.getDescricao());
			
			uSoap.setDataInicio(dateToXMLGregorianCalendar(u.getDataInicio()));
			uSoap.setDataFim(dateToXMLGregorianCalendar(u.getDataFim()));

			uSoaps.add(uSoap);
		});

		return response;
	}
}