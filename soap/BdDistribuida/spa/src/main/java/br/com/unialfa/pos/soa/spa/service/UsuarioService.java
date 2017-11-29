package br.com.unialfa.pos.soa.spa.service;

import java.util.List;

import br.com.unialfa.pos.soa.spa.core.to.UsuarioTo;

public interface UsuarioService {
	List<UsuarioTo> obtemTodosOsUsuarios();

	UsuarioTo gravaUsuario(UsuarioTo usuario) throws Exception;

	void removeUsuario(Long id);

	UsuarioTo obtemUsuarioPorId(Long id);
}
