package br.com.unialfa.pos.soa.usuarioService.soap.ws;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	private static final String NAMESPACE_USUARIOS_URI = "http://soa.pos.unialfa.com.br/soap/usuario-web-service";

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "usuarios")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usuariosSchema) {

		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("UsuariosPort");
		wsdl11Definition.setLocationUri("/ws/usuarios");
		wsdl11Definition.setTargetNamespace(NAMESPACE_USUARIOS_URI);
		wsdl11Definition.setSchema(usuariosSchema);

		return wsdl11Definition;
	}

	@Bean
	public XsdSchema usuariosSchema() {
		return new SimpleXsdSchema(new ClassPathResource("schemas/usuarios.xsd"));
	}
}