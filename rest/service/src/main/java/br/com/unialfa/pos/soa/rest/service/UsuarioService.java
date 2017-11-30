package br.com.unialfa.pos.soa.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.unialfa.pos.soa.rest.core.model.entity.Usuario;

public interface UsuarioService {
	Page<Usuario> obtemTodosOsUsuarios(Pageable pageable);

	Usuario gravaUsuario(Usuario usuario) throws Exception;

	void removeUsuario(Long id);

	Usuario obtemUsuarioPorId(Long id);
}
