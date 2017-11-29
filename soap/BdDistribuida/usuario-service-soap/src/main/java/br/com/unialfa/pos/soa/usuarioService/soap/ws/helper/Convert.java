package br.com.unialfa.pos.soa.usuarioService.soap.ws.helper;

import br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity.Usuario;

public class Convert {

	public static Usuario ServiceUsuarioToEntityUsuario(br.com.unialfa.pos.soa.soap.web_service.Usuario usuario) {
		Usuario u = new Usuario();
		u.setId(usuario.getId());
		u.setEmail(usuario.getEmail());
		u.setNome(usuario.getNome());
		
		return u;		
	}
	
	public static br.com.unialfa.pos.soa.soap.web_service.Usuario EntityUsuarioToServiceUsuario(Usuario usuario) {
		br.com.unialfa.pos.soa.soap.web_service.Usuario u = new br.com.unialfa.pos.soa.soap.web_service.Usuario();
		u.setId(usuario.getId());
		u.setEmail(usuario.getEmail());
		u.setNome(usuario.getNome());
		
		return u;		
	}
}
