package br.com.unialfa.pos.soa.rest.core.model.entity;

import javax.persistence.Column;
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
	@Column
	private Long remoteUsuarioId;

	@ManyToOne
	@JoinColumn(name = "tarefa_id", nullable = false)
	private Tarefa tarefa;
}
