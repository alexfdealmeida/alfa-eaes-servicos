package br.com.unialfa.pos.soa.spa.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import br.com.unialfa.pos.soa.spa.controller.ControllerPrincipal;
import br.com.unialfa.pos.soa.spa.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.spa.core.model.entity.Usuario;
import br.com.unialfa.pos.soa.spa.core.model.entity.UsuarioTarefa;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTarefaTo;
import br.com.unialfa.pos.soa.spa.service.TarefaService;
import br.com.unialfa.pos.soa.spa.service.UsuarioService;

@Controller
public class ControllerPrincipalImpl implements ControllerPrincipal {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	TarefaService tarefaService;

	private void carregaObjetosDaTela(ModelAndView mv, Boolean limpaForms) {

		if (limpaForms) {
			mv.addObject("usuario", new Usuario());
			mv.addObject("tarefa", new Tarefa());
			mv.addObject("usuarioTarefa", new UsuarioTarefaTo());
		}

		List<Usuario> users = this.usuarioService.obtemTodosOsUsuarios();
		// List<Usuario> users = users.stream().map(u -> new
		// Usuario(u)).collect(Collectors.toList());
		mv.addObject("usuarios", users);

		List<Tarefa> tasks = this.tarefaService.obtemTodasAsTarefas();
		// List<Tarefa> tasks = tasks.stream().map(t -> new
		// Tarefa(t)).collect(Collectors.toList());
		mv.addObject("tarefas", tasks);

	}

	private void carregaObjetosDaTela(ModelAndView mv, Boolean limpaForms, Long idTarefaSelecionada) {

		if (limpaForms) {
			mv.addObject("usuario", new Usuario());
			mv.addObject("tarefa", new Tarefa());
			mv.addObject("usuarioTarefa", new UsuarioTarefaTo());
		}

		List<Usuario> users = this.usuarioService.obtemTodosOsUsuarios();
		// List<Usuario> users = users.stream().map(u -> new
		// Usuario(u)).collect(Collectors.toList());
		mv.addObject("usuarios", users);

		List<Tarefa> tasks = this.tarefaService.obtemTodasAsTarefas();
		// List<Tarefa> tasks = tasks.stream().map(t -> new
		// Tarefa(t)).collect(Collectors.toList());
		mv.addObject("tarefas", tasks);

		if (idTarefaSelecionada != null) {
			List<UsuarioTarefa> uts = this.tarefaService.obtemTodasAsAlocacoes(idTarefaSelecionada);
			// List<UsuarioTarefa> uts = uts.stream().map(ut ->
			// ut)).collect(Collectors.toList());
			mv.addObject("usuariosTarefas", uts);
		}
	}

	@Override
	public ModelAndView spa(Model model) {
		ModelAndView mv = new ModelAndView("spa");

		this.carregaObjetosDaTela(mv, true);

		mv.addObject("abaAtiva", "usuarios");

		return mv;
	}

	@Override
	public String spaAddUser(@ModelAttribute Usuario usuario) {

		try {
			this.usuarioService.gravaUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/spa";
	}

	@Override
	public String spaDeleteUser(@PathVariable("id") Long id) {
		this.usuarioService.removeUsuario(id);

		return "redirect:/spa";
	}

	@Override
	public ModelAndView spaEditUser(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("spa");

		Usuario usuario = this.usuarioService.obtemUsuarioPorId(id);
		mv.addObject("usuario", usuario);

		mv.addObject("tarefa", new Tarefa());

		this.carregaObjetosDaTela(mv, false);

		mv.addObject("abaAtiva", "usuarios");

		return mv;
	}

	@Override
	public ModelAndView spaAddTarefa(@ModelAttribute Tarefa tarefa) {
		ModelAndView mv = new ModelAndView("spa");

		try {
			this.tarefaService.gravaTarefa(tarefa);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.carregaObjetosDaTela(mv, true);

		mv.addObject("abaAtiva", "tarefas");

		return mv;
	}

	// @InitBinder
	// public void initBinder(WebDataBinder binder) {
	// CustomDateEditor editor = new CustomDateEditor(new
	// SimpleDateFormat("yyyy-MM-dd"), true);
	// binder.registerCustomEditor(Date.class, editor);
	// }

	@Override
	public ModelAndView spaDeleteTarefa(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("spa");

		try {
			this.tarefaService.removeTarefa(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.carregaObjetosDaTela(mv, true);

		mv.addObject("abaAtiva", "tarefas");

		return mv;
	}

	@Override
	public ModelAndView spaEditTarefa(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("spa");

		Tarefa tarefa = this.tarefaService.obtemTarefaPorId(id);
		mv.addObject("tarefa", tarefa);

		mv.addObject("usuario", new Usuario());

		mv.addObject("usuarioTarefa", new UsuarioTarefaTo());

		this.carregaObjetosDaTela(mv, false);

		mv.addObject("abaAtiva", "tarefas");

		return mv;
	}

	@Override
	public ModelAndView spaAlocaTarefa(@ModelAttribute UsuarioTarefaTo usuarioTarefa) {
		ModelAndView mv = new ModelAndView("spa");
		UsuarioTarefa ut = null;
		try {
			ut = this.tarefaService.alocaTarefa(usuarioTarefa);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.carregaObjetosDaTela(mv, true, ut.getTarefa().getId());

		UsuarioTarefaTo to = new UsuarioTarefaTo();
		to.setIdTarefa(ut.getTarefa().getId());
		mv.addObject("usuarioTarefa", to);

		mv.addObject("abaAtiva", "tarefas-alocadas");

		return mv;
	}

	@Override
	public ModelAndView spaDesalocaTarefa(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("spa");
		UsuarioTarefa ut = null;
		try {
			ut = this.tarefaService.desalocaTarefa(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.carregaObjetosDaTela(mv, true, ut.getTarefa().getId());

		UsuarioTarefaTo to = new UsuarioTarefaTo();
		to.setIdTarefa(ut.getTarefa().getId());
		mv.addObject("usuarioTarefa", to);

		mv.addObject("abaAtiva", "tarefas-alocadas");

		return mv;
	}

	@Override
	public ModelAndView spaObtemUsuarioDaTarefaTarefa(@PathVariable("id") Long idTarefa) {
		ModelAndView mv = new ModelAndView("spa");

		this.carregaObjetosDaTela(mv, true, idTarefa);

		UsuarioTarefaTo to = new UsuarioTarefaTo();
		to.setIdTarefa(idTarefa);
		mv.addObject("usuarioTarefa", to);
		mv.addObject("abaAtiva", "tarefas-alocadas");

		return mv;
	}

}
