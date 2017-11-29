package br.com.unialfa.pos.soa.spa.core.soap;

import java.util.List;

import br.com.unialfa.pos.soa.spa.core.to.UsuarioTarefaTo;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTo;

public interface UsuariosSoapClient {

	public UsuarioTo findOne(Long id);

	public List<UsuarioTo> findByOrderByNomeAsc();

	public List<UsuarioTarefaTo> findByTarefaIdOrderByUsuarioNome(Long idTarefa);

	public UsuarioTo saveUsuario(UsuarioTo to);

	public void removeUsuario(Long usuarioRemotoId);

	public void removeTarefaDeTodosOsUsuarios(Long tarefaId);

	public Boolean alocaTarefaParaUsuario(Long tarefaId, Long usuarioId);

	// retorna o id da tarefa que foi desalocada
	public Long desalocaTarefaParaUsuario(Long usuarioTarefaId);

	// obtém o id da tarefa de uma alocação
	public Long obtemIdDeTarefaDeUmaAlocacao(Long usuarioTarefaId);

}
