package br.com.unialfa.pos.soa.spa.helper;

import br.com.unialfa.pos.soa.spa.core.to.UsuarioTo;
import servicos.wsdl.Usuario;

public class Convert {
	public static UsuarioTo usuarioServiceToUsuarioTo(Usuario us) {
		if (us == null) {
			return new UsuarioTo();
		}

		UsuarioTo to = new UsuarioTo();
		to.setId(us.getId());
		to.setEmail(us.getEmail());
		to.setNome(us.getNome());

		return to;
	}

	public static Usuario usuarioToToUsuarioService(UsuarioTo to) {
		if (to == null) {
			return new Usuario();
		}

		Usuario usuario = new Usuario();
		if (to.getId() != null) {
			usuario.setId(to.getId());
		}
		usuario.setEmail(to.getEmail());
		usuario.setNome(to.getNome());

		return usuario;
	}
}
