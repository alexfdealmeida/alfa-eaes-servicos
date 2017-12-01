package br.com.unialfa.pos.soa.usuarioService.soap.ws;

import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

import br.com.unialfa.pos.soa.usuarioService.soap.ws.logging.LogHttpHeaderEndpointInterceptor;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	private static final String NAMESPACE_URI = "http://soa.pos.unialfa.com.br/soap/web-service";

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "service")
	public DefaultWsdl11Definition defaultUsuariosWsdl11Definition(XsdSchemaCollection serviceSchema) {

		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ServicePort");
		wsdl11Definition.setLocationUri("/ws/service");
		wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
		wsdl11Definition.setSchemaCollection(serviceSchema);

		return wsdl11Definition;
	}

	@Bean
	public XsdSchemaCollection serviceSchema() {
		CommonsXsdSchemaCollection xsds = new CommonsXsdSchemaCollection(new ClassPathResource("schemas/main.xsd"));
		xsds.setInline(true);
		return xsds;
	}
	
	  @Override
	  public void addInterceptors(List<EndpointInterceptor> interceptors) {
	    interceptors.add(new LogHttpHeaderEndpointInterceptor());
	  }
	
}