package br.com.unialfa.pos.soa.spa.core.to;

import br.com.unialfa.pos.soa.spa.core.model.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioTarefaTo extends AbstractEntity {
	private Long idUsuarioTarefaRemota;
	private Long idTarefa;
	private UsuarioTo usuarioTo = new UsuarioTo();
}
