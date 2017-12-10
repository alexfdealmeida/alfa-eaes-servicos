package br.com.unialfa.pos.soa.infra.mensageria.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.unialfa.pos.soa.infra.mensageria.messages.incoming.UsuarioChangedIncomingMessage;

public interface UsuarioController {
	@PostMapping("/mensageria/usuario/changes")
	public ResponseEntity<Void> onReceivedMessageAboutUsuarioChanged(
			@RequestBody UsuarioChangedIncomingMessage message);
}
