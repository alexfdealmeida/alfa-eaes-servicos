package br.com.unialfa.pos.monolitico.service;

import java.util.List;

import br.com.unialfa.pos.monolitico.core.model.entity.Tarefa;
import br.com.unialfa.pos.monolitico.core.model.entity.UsuarioTarefa;
import br.com.unialfa.pos.monolitico.core.to.UsuarioTarefaTo;

public interface TarefaService {
	List<Tarefa> obtemTodasAsTarefas();

	Tarefa gravaTarefa(Tarefa tarefa) throws Exception;

	void removeTarefa(Long id);

	Tarefa obtemTarefaPorId(Long id);

	List<UsuarioTarefa> obtemTodasAsAlocacoes(Long idTarefaSelecionada);

	UsuarioTarefa alocaTarefa(UsuarioTarefaTo usuarioTarefa);

	UsuarioTarefa desalocaTarefa(Long id);
}
