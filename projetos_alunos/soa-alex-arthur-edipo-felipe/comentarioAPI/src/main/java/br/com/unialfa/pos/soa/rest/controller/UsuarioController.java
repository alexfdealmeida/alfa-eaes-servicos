package br.com.unialfa.pos.soa.rest.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.unialfa.pos.soa.rest.core.model.entity.Usuario;

@RequestMapping("/usuarios")
public interface UsuarioController {

	@GetMapping("/all")
	public ResponseEntity<Page<Usuario>> getAll(Pageable pageable);

	@PostMapping("/save-or-create")
	public ResponseEntity<Usuario> saveOrCretate(@RequestBody Usuario usuario) throws Exception;

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id);

}
