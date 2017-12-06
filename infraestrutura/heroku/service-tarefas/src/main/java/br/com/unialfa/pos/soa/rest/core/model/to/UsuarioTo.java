package br.com.unialfa.pos.soa.rest.core.model.to;

import lombok.Data;

@Data
public class UsuarioTo {
	private Long id;
	private String nome;
	private String email;
}
