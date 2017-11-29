package br.com.unialfa.pos.soa.spa.core.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "comentario")
@Data
@EqualsAndHashCode(callSuper = false)
public class Comentario extends AbstractEntity {
	@Column
	private String corpo;

	@Column
	private Date data;

	// O autor de um comentário é um usuário
	@Column(name="id_usuario")
	private Long idAutor;

	@OneToOne
	@JoinColumn(name = "tarefa_id", nullable = false)
	private Tarefa tarefa;
}
