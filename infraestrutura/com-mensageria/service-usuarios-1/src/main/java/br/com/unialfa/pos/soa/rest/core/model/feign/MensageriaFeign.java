package br.com.unialfa.pos.soa.rest.core.model.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.unialfa.pos.soa.rest.core.model.to.UsuarioChangedMessageTo;

@FeignClient("mensageria")
public interface MensageriaFeign {
	
	@PostMapping("/mensageria/usuario/changes")
	public void comunicaMudancaDeUsuario(@RequestBody UsuarioChangedMessageTo message);
}
