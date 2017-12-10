package br.com.unialfa.pos.soa.infra.mensageria.controllers;

public interface UsuarioMessageQueue {
	
	public void emittingNotificationAboutUsuarioChanged(Boolean isUsuarioDeleted);

}
