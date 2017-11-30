package br.com.unialfa.pos.soa.rest.core.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "usuario_tarefa")
@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioTarefa extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "tarefa_id", nullable = false)
	private Tarefa tarefa;
}
