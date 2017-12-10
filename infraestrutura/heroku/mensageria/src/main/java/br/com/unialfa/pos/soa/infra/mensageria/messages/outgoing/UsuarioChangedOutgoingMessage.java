package br.com.unialfa.pos.soa.infra.mensageria.messages.outgoing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioChangedOutgoingMessage {
	private Boolean isRemoved;
}
