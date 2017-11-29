package br.com.unialfa.pos.soa.spa.service;

import java.util.List;

import br.com.unialfa.pos.soa.spa.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTarefaTo;
import br.com.unialfa.pos.soa.spa.helper.tela.UsuarioTarefa;

public interface TarefaService {
	List<Tarefa> obtemTodasAsTarefas();

	Tarefa gravaTarefa(Tarefa tarefa) throws Exception;

	void removeTarefa(Long id);

	Tarefa obtemTarefaPorId(Long id);

	List<UsuarioTarefa> obtemTodasAsAlocacoes(Long idTarefaSelecionada);

	Boolean alocaTarefa(UsuarioTarefaTo usuarioTarefa);

	// retorna o id da Tarefa desalocada
	Long desalocaTarefa(Long usuarioTarefaRemotoId);
}
