package br.com.unialfa.pos.soa.infra.mensageria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MensageriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MensageriaApplication.class, args);
	}
}
