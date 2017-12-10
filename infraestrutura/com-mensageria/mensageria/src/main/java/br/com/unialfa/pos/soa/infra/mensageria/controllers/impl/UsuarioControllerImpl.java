package br.com.unialfa.pos.soa.infra.mensageria.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unialfa.pos.soa.infra.mensageria.controllers.UsuarioController;
import br.com.unialfa.pos.soa.infra.mensageria.controllers.UsuarioMessageQueue;
import br.com.unialfa.pos.soa.infra.mensageria.messages.incoming.UsuarioChangedIncomingMessage;

@RestController
public class UsuarioControllerImpl implements UsuarioController {
	
	@Autowired
	UsuarioMessageQueue usuarioMessageQueue;
	
	@Override
	public ResponseEntity<Void> onReceivedMessageAboutUsuarioChanged(@RequestBody UsuarioChangedIncomingMessage message) {
		this.usuarioMessageQueue.emittingNotificationAboutUsuarioChanged(message.getIsRemoved());
		
		return ResponseEntity.noContent().build();
	}
}
