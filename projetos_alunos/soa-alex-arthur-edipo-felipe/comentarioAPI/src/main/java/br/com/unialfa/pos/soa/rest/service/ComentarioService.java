package br.com.unialfa.pos.soa.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.unialfa.pos.soa.rest.core.model.entity.Comentario;

public interface ComentarioService {
	
	Page<Comentario> obtemTodosOsComentarios(Pageable pageable);
	
	Comentario gravaComentario(Comentario comentario) throws Exception;
	
	void removeComentario(Long id);
	
	Comentario obtemComentarioPorId(Long id);

}
