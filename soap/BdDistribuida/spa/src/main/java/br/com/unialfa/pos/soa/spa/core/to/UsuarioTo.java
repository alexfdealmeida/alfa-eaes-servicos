package br.com.unialfa.pos.soa.spa.core.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;


@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioTo extends AbstractEntityTo {

	private String nome;


	private String email;
	
	@Tolerate
	public UsuarioTo(servicos.wsdl.Usuario usuario) {
		super.setId(usuario.getId());
		this.setNome(usuario.getNome());
		this.setEmail(usuario.getEmail());
	}
	
}
