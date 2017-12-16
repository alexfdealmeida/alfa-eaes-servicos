package br.com.unialfa.pos.soa.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unialfa.pos.soa.rest.core.model.entity.Comentario;
import br.com.unialfa.pos.soa.rest.core.model.repository.ComentarioRepository;
import br.com.unialfa.pos.soa.rest.core.model.repository.TarefaRepository;
import br.com.unialfa.pos.soa.rest.core.model.repository.UsuarioRepository;
import br.com.unialfa.pos.soa.rest.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	TarefaRepository tarefaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Override
	public Page<Comentario> obtemTodosOsComentarios(Pageable pageable) {

		Page<Comentario> comentario = this.comentarioRepository.findAll(pageable);
		return comentario;
	}

	@Override
	public Comentario gravaComentario(Comentario comentario) {
		comentario = this.comentarioRepository.save(comentario);
		return comentario;
	}

	@Override
	@Transactional
	public void removeComentario(Long id) {
		
		Comentario comentario = this.comentarioRepository.findOne(id);
		this.comentarioRepository.delete(comentario);
		
	}

	@Override
	public Comentario obtemComentarioPorId(Long id) {
		Comentario comentario = this.comentarioRepository.findOne(id);
		return comentario;
	}
}
