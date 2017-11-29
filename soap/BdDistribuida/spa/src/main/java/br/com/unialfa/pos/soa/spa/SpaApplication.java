package br.com.unialfa.pos.soa.spa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.unialfa.pos.soa.spa.core.soap.UsuariosSoapClient;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTo;

@SpringBootApplication
public class SpaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpaApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(UsuariosSoapClient usuariosSoapClient) {
		return args -> {
			List<UsuarioTo> usuarios = usuariosSoapClient.findByOrderByNomeAsc();
			usuarios.forEach(u -> log.info(u.getNome()));
			
//			Long id = usuariosSoapClient.obtemIdDeTarefaDeUmaAlocacao(3L);
//			log.info("id da tarefa alocada: " + id);

		};
	}
}
