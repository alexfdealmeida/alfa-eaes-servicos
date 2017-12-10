package br.com.unialfa.pos.soa.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unialfa.pos.soa.rest.core.model.entity.Usuario;
import br.com.unialfa.pos.soa.rest.core.model.feign.MensageriaFeign;
import br.com.unialfa.pos.soa.rest.core.model.repository.UsuarioRepository;
import br.com.unialfa.pos.soa.rest.core.model.to.UsuarioChangedMessageTo;
import br.com.unialfa.pos.soa.rest.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	MensageriaFeign mensageriaFeign;

	@Override
	public Page<Usuario> obtemTodosOsUsuarios(Pageable pageable) {

		Page<Usuario> usuarios = this.usuarioRepository.findAll(pageable);

		return usuarios;

	}

	@Override
	public List<Usuario> obtemTodosOsUsuariosByIdIn(List<Long> ids) {
		List<Usuario> usuarios = this.usuarioRepository.findByIdIn(ids);

		return usuarios;
	}

	@Override
	public Usuario gravaUsuario(Usuario usuario) {
		usuario = this.usuarioRepository.save(usuario);

		UsuarioChangedMessageTo to = UsuarioChangedMessageTo.builder().isRemoved(false).build();

		this.mensageriaFeign.comunicaMudancaDeUsuario(to);

		return usuario;
	}

	@Override
	@Transactional
	public void removeUsuario(Long id) {
		this.usuarioRepository.delete(id);

		UsuarioChangedMessageTo to = UsuarioChangedMessageTo.builder().isRemoved(false).build();

		this.mensageriaFeign.comunicaMudancaDeUsuario(to);
	}

	@Override
	public Usuario obtemUsuarioPorId(Long id) {
		Usuario usuario = this.usuarioRepository.findOne(id);
		return usuario;
	}

}
