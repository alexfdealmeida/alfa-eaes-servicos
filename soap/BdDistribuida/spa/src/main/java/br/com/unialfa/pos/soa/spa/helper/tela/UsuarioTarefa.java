package br.com.unialfa.pos.soa.spa.helper.tela;

import br.com.unialfa.pos.soa.spa.core.model.entity.Tarefa;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class UsuarioTarefa {
	// id da tarefa remota
	private Long id;

	private Tarefa tarefa;

	private String nomeUsuario;

	@Tolerate
	public UsuarioTarefa(Long idUsuarioTarefaRemoto, Tarefa tarefa, String nomeUsuario) {
		this.setId(idUsuarioTarefaRemoto);
		this.setTarefa(tarefa);
		this.setNomeUsuario(nomeUsuario);
	}

}
