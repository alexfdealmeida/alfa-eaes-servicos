package br.com.unialfa.pos.soa.infra.mensageria.messages.incoming;

import lombok.Data;

@Data
public class UsuarioChangedIncomingMessage {
	private Boolean isRemoved;
}
