package br.com.unialfa.pos.soa.rest.core.model.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.unialfa.pos.soa.rest.core.model.to.UsuarioTo;

@FeignClient("usuarios")
public interface UsuarioFeign {

	@PostMapping("/usuarios/usuarios-by-id-in")
	List<UsuarioTo> findByIdIn(List<Long> usuariosIds);

}
