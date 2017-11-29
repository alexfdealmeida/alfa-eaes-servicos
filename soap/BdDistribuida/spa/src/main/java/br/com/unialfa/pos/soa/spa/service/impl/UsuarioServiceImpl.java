package br.com.unialfa.pos.soa.spa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unialfa.pos.soa.spa.core.model.repository.ComentarioRepository;
import br.com.unialfa.pos.soa.spa.core.soap.UsuariosSoapClient;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTo;
import br.com.unialfa.pos.soa.spa.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuariosSoapClient usuariosSoapClient;
	
	@Autowired
	ComentarioRepository comentarioRepository;

	@Override
	public List<UsuarioTo> obtemTodosOsUsuarios() {

		List<UsuarioTo> usuarios = this.usuariosSoapClient.findByOrderByNomeAsc();

		return usuarios;

	}

	@Override
	public UsuarioTo gravaUsuario(UsuarioTo usuario) {
		usuario = this.usuariosSoapClient.saveUsuario(usuario);
		return usuario;
	}

	@Override
	@Transactional
	public void removeUsuario(Long idUsuarioRemoto) {
		this.usuariosSoapClient.removeUsuario(idUsuarioRemoto);
	}

	@Override
	public UsuarioTo obtemUsuarioPorId(Long id) {		
		UsuarioTo usuario = this.usuariosSoapClient.findOne(id);
		return usuario;
	}

}
