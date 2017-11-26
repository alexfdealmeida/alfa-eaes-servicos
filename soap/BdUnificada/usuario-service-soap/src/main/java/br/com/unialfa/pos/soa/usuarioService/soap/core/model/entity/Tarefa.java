package br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tarefa")
@Data
@EqualsAndHashCode(callSuper = false)
public class Tarefa extends AbstractEntity {
	@Column
	private String titulo;

	@Column
	private String descricao;

	@Column
	private Date dataInicio;

	@Column
	private Date dataFim;
}
