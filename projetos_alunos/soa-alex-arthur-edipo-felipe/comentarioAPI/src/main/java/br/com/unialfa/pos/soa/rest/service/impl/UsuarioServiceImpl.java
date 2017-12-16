package br.com.unialfa.pos.soa.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unialfa.pos.soa.rest.core.model.entity.Comentario;
import br.com.unialfa.pos.soa.rest.core.model.entity.Usuario;
import br.com.unialfa.pos.soa.rest.core.model.entity.UsuarioTarefa;
import br.com.unialfa.pos.soa.rest.core.model.repository.ComentarioRepository;
import br.com.unialfa.pos.soa.rest.core.model.repository.UsuarioRepository;
import br.com.unialfa.pos.soa.rest.core.model.repository.UsuarioTarefaRepository;
import br.com.unialfa.pos.soa.rest.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioTarefaRepository usuarioTarefaRepository;
	
	@Autowired
	ComentarioRepository comentarioRepository;

	@Override
	public Page<Usuario> obtemTodosOsUsuarios(Pageable pageable) {

		Page<Usuario> usuarios = this.usuarioRepository.findAll(pageable);
		return usuarios;

	}

	@Override
	public Usuario gravaUsuario(Usuario usuario) {
		usuario = this.usuarioRepository.save(usuario);
		return usuario;
	}

	@Override
	@Transactional
	public void removeUsuario(Long id) {
		List<UsuarioTarefa> uts = this.usuarioTarefaRepository.findByUsuarioId(id);
		this.usuarioTarefaRepository.delete(uts);
		
		List<Comentario> comentarios = this.comentarioRepository.findByAutorId(id);
		this.comentarioRepository.delete(comentarios);

		this.usuarioRepository.delete(id);
	}

	@Override
	public Usuario obtemUsuarioPorId(Long id) {
		Usuario usuario = this.usuarioRepository.findOne(id);
		return usuario;
	}

}
