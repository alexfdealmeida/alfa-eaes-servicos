package br.com.unialfa.pos.soa.spa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unialfa.pos.soa.spa.core.model.entity.Comentario;
import br.com.unialfa.pos.soa.spa.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.spa.core.model.entity.Usuario;
import br.com.unialfa.pos.soa.spa.core.model.entity.UsuarioTarefa;
import br.com.unialfa.pos.soa.spa.core.model.repository.ComentarioRepository;
import br.com.unialfa.pos.soa.spa.core.model.repository.TarefaRepository;
import br.com.unialfa.pos.soa.spa.core.model.repository.UsuarioRepository;
import br.com.unialfa.pos.soa.spa.core.model.repository.UsuarioTarefaRepository;
import br.com.unialfa.pos.soa.spa.core.soap.TarefasSoapClient;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTarefaTo;
import br.com.unialfa.pos.soa.spa.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	TarefasSoapClient tarefasSoapClient;
	
	@Autowired
	TarefaRepository tarefaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ComentarioRepository comentarioRepository;

	@Autowired
	UsuarioTarefaRepository usuarioTarefaRepository;

	@Override
	public List<Tarefa> obtemTodasAsTarefas() {

//		List<Tarefa> tarefas = this.tarefaRepository.findByOrderByDataInicioDesc();

		List<Tarefa> tarefas = this.tarefasSoapClient.getAllTarefas();
		
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
		List<UsuarioTarefa> uts = this.usuarioTarefaRepository.findByTarefaIdOrderByUsuarioNome(idTarefaSelecionada);
		return uts;
	}

	@Override
	public UsuarioTarefa alocaTarefa(UsuarioTarefaTo usuarioTarefaTo) {
		UsuarioTarefa ut = new UsuarioTarefa();

		Usuario usuario = this.usuarioRepository.findOne(usuarioTarefaTo.getIdUsuario());

		Tarefa tarefa = this.tarefaRepository.findOne(usuarioTarefaTo.getIdTarefa());

		ut.setTarefa(tarefa);
		ut.setUsuario(usuario);

		ut = this.usuarioTarefaRepository.save(ut);

		return ut;
	}

	@Override
	public UsuarioTarefa desalocaTarefa(Long id) {
		UsuarioTarefa ut = this.usuarioTarefaRepository.findOne(id);
		this.usuarioTarefaRepository.delete(id);

		return ut;

	}

}
