package br.com.unialfa.pos.soa.rest.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unialfa.pos.soa.rest.controller.ComentarioController;
import br.com.unialfa.pos.soa.rest.core.model.entity.Comentario;
import br.com.unialfa.pos.soa.rest.service.ComentarioService;

@RestController
public class ComentarioControllerImpl implements ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;

	public ResponseEntity<Page<Comentario>> getAll(Pageable pageable) {
		Page<Comentario> comentarioPage = this.comentarioService.obtemTodosOsComentarios(pageable);

		return new ResponseEntity<Page<Comentario>>(comentarioPage,HttpStatus.OK);
	}

	public ResponseEntity<Comentario> saveOrCretate(@RequestBody Comentario comentario) throws Exception {
		comentario = this.comentarioService.gravaComentario(comentario);
		
		return new ResponseEntity<Comentario>(comentario,HttpStatus.OK);
	}

	public ResponseEntity<Void> remove(@PathVariable Long id) {
		this.comentarioService.removeComentario(id);
		
		return ResponseEntity.noContent().build();
	}
}
