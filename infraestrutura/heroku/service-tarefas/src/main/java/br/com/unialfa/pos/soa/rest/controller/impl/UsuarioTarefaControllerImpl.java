package br.com.unialfa.pos.soa.rest.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unialfa.pos.soa.rest.controller.UsuarioTarefaController;
import br.com.unialfa.pos.soa.rest.core.model.entity.UsuarioTarefa;
import br.com.unialfa.pos.soa.rest.core.model.repository.UsuarioTarefaRepository;
import br.com.unialfa.pos.soa.rest.core.model.to.UsuarioTarefaTo;
import br.com.unialfa.pos.soa.rest.service.TarefaService;

@RestController
public class UsuarioTarefaControllerImpl implements UsuarioTarefaController {

	@Autowired
	private TarefaService tarefaService;

	@Autowired
	private UsuarioTarefaRepository usuarioTarefaRepository;

	public ResponseEntity<Page<UsuarioTarefaTo>> findByTarefaId(@PathVariable Long idTarefa,
			@RequestParam(name = "sort-by-name") String order, Pageable pageable) {
		Page<UsuarioTarefaTo> usuarioTarefaToPage = this.tarefaService.obtemTodosOsUsuariosDeUmaTarefa(idTarefa, order,
				pageable);

		return new ResponseEntity<Page<UsuarioTarefaTo>>(usuarioTarefaToPage, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> removeTarefasDoUsuario(@PathVariable Long usuarioId) {

		try {
			List<UsuarioTarefa> uts = this.usuarioTarefaRepository.findByRemoteUsuarioId(usuarioId);

			this.usuarioTarefaRepository.delete(uts);

			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}

	public ResponseEntity<UsuarioTarefaTo> saveOrCretate(@RequestBody UsuarioTarefaTo usuarioTarefaTo)
			throws Exception {
		usuarioTarefaTo = this.tarefaService.gravaUsuarioTarefa(usuarioTarefaTo);

		return new ResponseEntity<UsuarioTarefaTo>(usuarioTarefaTo, HttpStatus.OK);
	}

	public ResponseEntity<Void> remove(@PathVariable Long id) {
		this.tarefaService.removeUsuarioTarefa(id);

		return ResponseEntity.noContent().build();
	}

}
