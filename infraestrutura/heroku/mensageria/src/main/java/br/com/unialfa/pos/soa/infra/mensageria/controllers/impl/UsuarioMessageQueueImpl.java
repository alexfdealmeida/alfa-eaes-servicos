package br.com.unialfa.pos.soa.infra.mensageria.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import br.com.unialfa.pos.soa.infra.mensageria.controllers.UsuarioMessageQueue;
import br.com.unialfa.pos.soa.infra.mensageria.messages.outgoing.UsuarioChangedOutgoingMessage;

@Controller
public class UsuarioMessageQueueImpl implements UsuarioMessageQueue {
	
	@Autowired
	private SimpMessagingTemplate template;

	@Override
	public void emittingNotificationAboutUsuarioChanged(Boolean isUsuarioDeleted) {
		UsuarioChangedOutgoingMessage message = UsuarioChangedOutgoingMessage.builder().isRemoved(isUsuarioDeleted)
				.build();
		
		this.template.convertAndSend("/usuarios-notifications", message);
	}

}
