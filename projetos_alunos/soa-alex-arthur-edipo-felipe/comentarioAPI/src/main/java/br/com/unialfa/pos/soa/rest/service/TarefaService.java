package br.com.unialfa.pos.soa.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.unialfa.pos.soa.rest.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.rest.core.model.entity.UsuarioTarefa;

public interface TarefaService {
		
	Page<Tarefa> obtemTodasAsTarefas(Pageable pageable);

	Tarefa gravaTarefa(Tarefa tarefa) throws Exception;

	void removeTarefa(Long id);

	Tarefa obtemTarefaPorId(Long id);

	List<UsuarioTarefa> obtemTodasAsAlocacoes(Long idTarefaSelecionada);

	UsuarioTarefa alocaTarefa(UsuarioTarefa usuarioTarefa);

	UsuarioTarefa desalocaTarefa(Long id);
}
