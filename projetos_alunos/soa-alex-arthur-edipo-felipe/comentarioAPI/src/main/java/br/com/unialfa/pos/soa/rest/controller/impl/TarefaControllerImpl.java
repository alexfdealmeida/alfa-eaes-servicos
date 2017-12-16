package br.com.unialfa.pos.soa.rest.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unialfa.pos.soa.rest.controller.TarefaController;
import br.com.unialfa.pos.soa.rest.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.rest.service.TarefaService;

@RestController
public class TarefaControllerImpl implements TarefaController {

	@Autowired
	private TarefaService tarefaService;

	public ResponseEntity<Page<Tarefa>> getAll(Pageable pageable) {
		Page<Tarefa> tarefaPage = this.tarefaService.obtemTodasAsTarefas(pageable);

		return new ResponseEntity<Page<Tarefa>>(tarefaPage,HttpStatus.OK);
	}

	public ResponseEntity<Tarefa> saveOrCretate(@RequestBody Tarefa tarefa) throws Exception {
		tarefa = this.tarefaService.gravaTarefa(tarefa);
		
		return new ResponseEntity<Tarefa>(tarefa,HttpStatus.OK);
	}

	public ResponseEntity<Void> remove(@PathVariable Long id) {
		this.tarefaService.removeTarefa(id);
		
		return ResponseEntity.noContent().build();
	}

}
