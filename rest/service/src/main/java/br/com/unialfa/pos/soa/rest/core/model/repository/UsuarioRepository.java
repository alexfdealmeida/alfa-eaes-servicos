package br.com.unialfa.pos.soa.rest.core.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unialfa.pos.soa.rest.core.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByOrderByNomeAsc();
	
	Page<Usuario> findBy(Pageable pageable);
}
