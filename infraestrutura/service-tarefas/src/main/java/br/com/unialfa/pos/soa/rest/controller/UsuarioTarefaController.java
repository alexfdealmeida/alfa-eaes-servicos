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
import org.springframework.web.bind.annotation.RequestParam;

import br.com.unialfa.pos.soa.rest.core.model.to.UsuarioTarefaTo;

@RequestMapping("/usuarios-tarefas")
public interface UsuarioTarefaController {

	@GetMapping("/find-by-tarefa-id/{idTarefa}")
	public ResponseEntity<Page<UsuarioTarefaTo>> findByTarefaId(@PathVariable Long idTarefa,
			@RequestParam(name = "sort-by-name") String order, Pageable pageable);

	@PostMapping("/save-or-create")
	public ResponseEntity<UsuarioTarefaTo> saveOrCretate(@RequestBody UsuarioTarefaTo usuarioTarefa) throws Exception;

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id);

	@DeleteMapping("/remove-tarefas-do-usuario/{usuarioId}")
	public ResponseEntity<Boolean> removeTarefasDoUsuario(@PathVariable Long usuarioId);

}
