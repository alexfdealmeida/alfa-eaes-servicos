package br.com.unialfa.pos.soa.rest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unialfa.pos.soa.rest.core.model.entity.Comentario;
import br.com.unialfa.pos.soa.rest.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.rest.core.model.entity.UsuarioTarefa;
import br.com.unialfa.pos.soa.rest.core.model.feign.UsuarioFeign;
import br.com.unialfa.pos.soa.rest.core.model.repository.ComentarioRepository;
import br.com.unialfa.pos.soa.rest.core.model.repository.TarefaRepository;
import br.com.unialfa.pos.soa.rest.core.model.repository.UsuarioTarefaRepository;
import br.com.unialfa.pos.soa.rest.core.model.to.UsuarioTarefaTo;
import br.com.unialfa.pos.soa.rest.core.model.to.UsuarioTo;
import br.com.unialfa.pos.soa.rest.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	TarefaRepository tarefaRepository;

	@Autowired
	UsuarioFeign usuarioFeign;

	@Autowired
	ComentarioRepository comentarioRepository;

	@Autowired
	UsuarioTarefaRepository usuarioTarefaRepository;

	@Override
	public Page<Tarefa> obtemTodasAsTarefas(Pageable pageable) {

		Page<Tarefa> tarefas = this.tarefaRepository.findAll(pageable);

		return tarefas;

	}

	@Override
	public Tarefa gravaTarefa(Tarefa tarefa) {
		tarefa = this.tarefaRepository.save(tarefa);
		return tarefa;
	}

	@Override
	@Transactional
	public void removeTarefa(Long id) {

		List<UsuarioTarefa> uts = this.usuarioTarefaRepository.findByTarefaId(id);

		this.usuarioTarefaRepository.delete(uts);

		this.tarefaRepository.delete(id);

		List<Comentario> comentarios = this.comentarioRepository.findByTarefaId(id);

		this.comentarioRepository.delete(comentarios);
	}

	@Override
	public Tarefa obtemTarefaPorId(Long id) {
		Tarefa tarefa = this.tarefaRepository.findOne(id);
		return tarefa;
	}

	@Override
	public List<UsuarioTarefa> obtemTodasAsAlocacoes(Long idTarefaSelecionada) {
		List<UsuarioTarefa> uts = this.usuarioTarefaRepository.findByTarefaId(idTarefaSelecionada);
		return uts;
	}

	@Override
	public UsuarioTarefaTo alocaTarefa(UsuarioTarefaTo usuarioTarefaTo) {
		UsuarioTarefa ut = new UsuarioTarefa();

		Tarefa tarefa = this.tarefaRepository.findOne(usuarioTarefaTo.getTarefa().getId());

		ut.setTarefa(tarefa);
		ut.setRemoteUsuarioId(usuarioTarefaTo.getUsuario().getId());

		ut = this.usuarioTarefaRepository.save(ut);

		usuarioTarefaTo.setId(ut.getId());

		return usuarioTarefaTo;
	}

	@Override
	public UsuarioTarefa desalocaTarefa(Long id) {
		UsuarioTarefa ut = this.usuarioTarefaRepository.findOne(id);
		this.usuarioTarefaRepository.delete(id);

		return ut;

	}

	@Override
	public Page<UsuarioTarefaTo> obtemTodosOsUsuariosDeUmaTarefa(Long idTarefa, String order, Pageable pageable) {
		Page<UsuarioTarefa> usuarioTarefaPage = this.usuarioTarefaRepository.findByTarefaId(pageable, idTarefa);

		Tarefa tarefa = null;
		if (usuarioTarefaPage.hasContent()) {
			tarefa = usuarioTarefaPage.getContent().get(0).getTarefa();
		}

		List<Long> usuariosIds = usuarioTarefaPage.getContent().stream().map(ut -> ut.getRemoteUsuarioId()).distinct()
				.collect(Collectors.toList());

		List<UsuarioTo> uTos = this.usuarioFeign.findByIdIn(usuariosIds);

		Tarefa finalTarefa = tarefa;
		List<UsuarioTarefaTo> usuariosTarefasTo = usuarioTarefaPage.getContent().stream().map(ut -> {
			UsuarioTarefaTo uTTo = new UsuarioTarefaTo();
			uTTo.setId(ut.getId());
			uTTo.setTarefa(finalTarefa);
			uTTo.setUsuario(
					uTos.stream().filter(p -> p.getId().equals(ut.getRemoteUsuarioId())).findFirst().orElse(null));
			if (uTTo.getUsuario() != null) {
				return uTTo;
			} else {
				return null;
			}
		}).filter(u -> u != null).collect(Collectors.toList());

		if (order.equals("asc")) {
			usuariosTarefasTo.sort((a, b) -> a.getUsuario().getNome().compareTo(b.getUsuario().getNome()));
		} else {
			usuariosTarefasTo.sort((a, b) -> b.getUsuario().getNome().compareTo(a.getUsuario().getNome()));
		}

		Page<UsuarioTarefaTo> usuarioTarefaToPage = new PageImpl<UsuarioTarefaTo>(usuariosTarefasTo, pageable,
				usuarioTarefaPage.getTotalElements());

		return usuarioTarefaToPage;
	}

	@Override
	public UsuarioTarefaTo gravaUsuarioTarefa(UsuarioTarefaTo usuarioTarefaTo) {
		UsuarioTarefa ut = new UsuarioTarefa();
		ut.setRemoteUsuarioId(usuarioTarefaTo.getUsuario().getId());
		ut.setTarefa(usuarioTarefaTo.getTarefa());

		ut = this.usuarioTarefaRepository.save(ut);

		usuarioTarefaTo.setId(ut.getId());

		return usuarioTarefaTo;
	}

	@Override
	public void removeUsuarioTarefa(Long id) {
		this.usuarioTarefaRepository.delete(id);
	}

}
