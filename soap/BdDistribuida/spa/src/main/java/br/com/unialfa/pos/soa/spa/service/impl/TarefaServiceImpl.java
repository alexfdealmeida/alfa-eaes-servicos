package br.com.unialfa.pos.soa.spa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unialfa.pos.soa.spa.core.model.entity.Comentario;
import br.com.unialfa.pos.soa.spa.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.spa.core.model.repository.ComentarioRepository;
import br.com.unialfa.pos.soa.spa.core.model.repository.TarefaRepository;
import br.com.unialfa.pos.soa.spa.core.soap.UsuariosSoapClient;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTarefaTo;
import br.com.unialfa.pos.soa.spa.helper.tela.UsuarioTarefa;
import br.com.unialfa.pos.soa.spa.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	UsuariosSoapClient usuariosSoapClient;

	@Autowired
	TarefaRepository tarefaRepository;

	@Autowired
	ComentarioRepository comentarioRepository;

	@Override
	public List<Tarefa> obtemTodasAsTarefas() {

		List<Tarefa> tarefas = this.tarefaRepository.findByOrderByDataInicioDesc();

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

		// remove a tarefa de todos os usuários
		this.usuariosSoapClient.removeTarefaDeTodosOsUsuarios(id);

		// remove os comentarios da tarefa
		List<Comentario> comentarios = this.comentarioRepository.findByTarefaId(id);
		this.comentarioRepository.delete(comentarios);

		this.tarefaRepository.delete(id);
	}

	@Override
	public Tarefa obtemTarefaPorId(Long id) {
		Tarefa tarefa = this.tarefaRepository.findOne(id);
		return tarefa;
	}

	@Override
	public List<UsuarioTarefa> obtemTodasAsAlocacoes(Long idTarefaSelecionada) {
		List<UsuarioTarefaTo> utTos = this.usuariosSoapClient.findByTarefaIdOrderByUsuarioNome(idTarefaSelecionada);

		Tarefa tarefa = this.tarefaRepository.findOne(idTarefaSelecionada);

		List<UsuarioTarefa> uts = new ArrayList<>();

		utTos.forEach(utTo -> {
			UsuarioTarefa ut = new UsuarioTarefa(utTo.getIdUsuarioTarefaRemota(), tarefa,
					utTo.getUsuarioTo().getNome());

			uts.add(ut);
		});

		return uts;
	}

	@Override
	public Boolean alocaTarefa(UsuarioTarefaTo usuarioTarefaTo) {
		Boolean sucesso = this.usuariosSoapClient.alocaTarefaParaUsuario(usuarioTarefaTo.getIdTarefa(),
				usuarioTarefaTo.getUsuarioTo().getId());

		return sucesso;
	}

	@Override
	public Long desalocaTarefa(Long usuarioTarefaRemotoId) {
		Long idTarefa = this.usuariosSoapClient.desalocaTarefaParaUsuario(usuarioTarefaRemotoId);

		return idTarefa;

	}

}
