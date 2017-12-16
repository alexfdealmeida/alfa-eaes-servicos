package br.com.unialfa.pos.soa.rest.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unialfa.pos.soa.rest.controller.UsuarioController;
import br.com.unialfa.pos.soa.rest.core.model.entity.Usuario;
import br.com.unialfa.pos.soa.rest.service.UsuarioService;

@RestController
public class UsuarioControllerImpl implements UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	public ResponseEntity<Page<Usuario>> getAll(Pageable pageable) {
		Page<Usuario> usuarioPage = this.usuarioService.obtemTodosOsUsuarios(pageable);

		return new ResponseEntity<Page<Usuario>>(usuarioPage,HttpStatus.OK);
	}

	public ResponseEntity<Usuario> saveOrCretate(@RequestBody Usuario usuario) throws Exception {
		usuario = this.usuarioService.gravaUsuario(usuario);
		
		return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
	}

	public ResponseEntity<Void> remove(@PathVariable Long id) {
		this.usuarioService.removeUsuario(id);
		
		return ResponseEntity.noContent().build();
	}

}
