package br.com.unialfa.pos.soa.usuarioService.soap.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByOrderByNomeAsc();
	
	List<Usuario> findByNomeIgnoreCaseContainingOrderByNomeAsc(String parteDoNome);
}
