package br.com.unialfa.pos.soa.rest.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Data
@EqualsAndHashCode(callSuper = false)
public class Usuario extends AbstractEntity {
	@Column(nullable = false)
	String nome;

	@Column(nullable = false)
	String email;
}
