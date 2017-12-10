package br.com.unialfa.pos.soa.rest.core.model.to;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioChangedMessageTo {
	Boolean isRemoved;
}
