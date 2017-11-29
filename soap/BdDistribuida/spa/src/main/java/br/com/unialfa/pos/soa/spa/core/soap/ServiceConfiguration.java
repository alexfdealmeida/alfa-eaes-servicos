package br.com.unialfa.pos.soa.spa.core.soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import br.com.unialfa.pos.soa.spa.core.soap.impl.UsuariosSoapClientImpl;

@Configuration
public class ServiceConfiguration {

	private static final String WSDL_NAME = "servicos.wsdl";
	private static final String WS_SERVICE_DEFAULT_URI = "http://localhost:9000/ws/service";

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath(WSDL_NAME);
		return marshaller;
	}

	@Bean
	public UsuariosSoapClient usuariosSoapClient(Jaxb2Marshaller marshaller) {
		UsuariosSoapClientImpl client = new UsuariosSoapClientImpl();
		client.setDefaultUri(WS_SERVICE_DEFAULT_URI);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
}
