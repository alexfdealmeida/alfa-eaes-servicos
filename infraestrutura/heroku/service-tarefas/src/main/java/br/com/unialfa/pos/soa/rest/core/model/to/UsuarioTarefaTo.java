package br.com.unialfa.pos.soa.rest.core.model.to;

import br.com.unialfa.pos.soa.rest.core.model.entity.Tarefa;
import lombok.Data;

@Data
public class UsuarioTarefaTo {
	private Long id;
	private UsuarioTo usuario;
	private Tarefa tarefa;
}
