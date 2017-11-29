package br.com.unialfa.pos.soa.spa.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unialfa.pos.soa.spa.core.model.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {

	List<Comentario> findByIdAutor(Long id);
	
	List<Comentario> findByTarefaId(Long id);

}
