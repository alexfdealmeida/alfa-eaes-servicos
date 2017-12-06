package br.com.unialfa.pos.soa.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.unialfa.pos.soa.rest.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.rest.core.model.entity.UsuarioTarefa;
import br.com.unialfa.pos.soa.rest.core.model.to.UsuarioTarefaTo;

public interface TarefaService {
	Page<Tarefa> obtemTodasAsTarefas(Pageable pageable);

	Tarefa gravaTarefa(Tarefa tarefa) throws Exception;

	void removeTarefa(Long id);

	Tarefa obtemTarefaPorId(Long id);

	List<UsuarioTarefa> obtemTodasAsAlocacoes(Long idTarefaSelecionada);

	UsuarioTarefaTo alocaTarefa(UsuarioTarefaTo usuarioTarefa);

	UsuarioTarefa desalocaTarefa(Long id);

	Page<UsuarioTarefaTo> obtemTodosOsUsuariosDeUmaTarefa(Long idTarefa, String order, Pageable pageable);

	UsuarioTarefaTo gravaUsuarioTarefa(UsuarioTarefaTo usuarioTarefaTo);

	void removeUsuarioTarefa(Long id);
}
