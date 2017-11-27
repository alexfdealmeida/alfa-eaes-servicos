package br.com.unialfa.pos.soa.spa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.unialfa.pos.soa.spa.core.soap.UsuariosSoapClient;
import servicos.wsdl.GetUsuariosByPartOfNameResponse;

@SpringBootApplication
public class SpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(UsuariosSoapClient usuariosSoapClient) {
		return args -> {
			String parteDoNome = "an";

			GetUsuariosByPartOfNameResponse response = usuariosSoapClient.getUsuariosByPartOfName(parteDoNome);
			response.getUsuarios().forEach(u -> System.err.println(u.getNome()));
			
		};
	}
}
