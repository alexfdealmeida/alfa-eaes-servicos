package br.com.unialfa.pos.soa.spa.service;

import java.util.List;

import br.com.unialfa.pos.soa.spa.core.model.entity.Usuario;

public interface UsuarioService {
	List<Usuario> obtemTodosOsUsuarios();

	Usuario gravaUsuario(Usuario usuario) throws Exception;

	void removeUsuario(Long id);

	Usuario obtemUsuarioPorId(Long id);
}
