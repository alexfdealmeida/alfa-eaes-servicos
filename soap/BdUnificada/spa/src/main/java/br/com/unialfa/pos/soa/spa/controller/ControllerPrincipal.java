package br.com.unialfa.pos.soa.spa.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.unialfa.pos.soa.spa.core.model.entity.Tarefa;
import br.com.unialfa.pos.soa.spa.core.model.entity.Usuario;
import br.com.unialfa.pos.soa.spa.core.to.UsuarioTarefaTo;

public interface ControllerPrincipal {

	@GetMapping("/spa")
	public ModelAndView spa(Model model);
	
	@PostMapping("/spa/add-user")
	public String spaAddUser(@ModelAttribute Usuario usuario);
	
	@GetMapping("/spa/delete-user/{id}")
	public String spaDeleteUser(@PathVariable("id") Long id);
	
	@GetMapping("/spa/edit-user/{id}")
	public ModelAndView spaEditUser(@PathVariable("id") Long id);
	
	@PostMapping("/spa/add-tarefa")
	public String spaAddTarefa(@ModelAttribute Tarefa tarefa);
	
	@GetMapping("/spa/delete-tarefa/{id}")
	public String spaDeleteTarefa(@PathVariable("id") Long id);
	
	@GetMapping("/spa/edit-tarefa/{id}")
	public ModelAndView spaEditTarefa(@PathVariable("id") Long id);
	
	@PostMapping("/spa/aloca-tarefa-usuario")
	public ModelAndView spaAlocaTarefa(@ModelAttribute UsuarioTarefaTo usuarioTarefa);
	
	@GetMapping("/spa/desaloca-tarefa-usuario/{id}")
	public ModelAndView spaDesalocaTarefa(@PathVariable("id") Long id);
	
	@GetMapping("/spa/obtem-usuarios-tarefa/{id}")
	public ModelAndView spaObtemUsuarioDaTarefaTarefa(@PathVariable("id") Long id);

}
